const {sequelize, 
    Member, 
    GymClass,
    Trainer,
    Exercise,
    FitnessClass,
    FitnessPortfolio,
    Sequelize,
    GymLocation} 
= require('./models');
const {uuid} = require('uuidv4');
const Op = Sequelize.Op;
const bcrypt = require('bcrypt');

module.exports.init = ()=>{
    return new Promise((resolve,reject)=>{
        sequelize.authenticate()
        .then(()=> resolve('Database successfully authenticated'))
        .catch(err=>reject(err));
    });
    
}


module.exports.getAllMembers=async()=>{
        const members = await Member.findAll();
        return members;
}
//retrieves Gymclasses with trainer, fitness class, and gymlocation data
module.exports.getUpcomingGymClasses=()=>{
   return new Promise((resolve,reject)=>{
    GymClass.findAll({
        include: ['trainer','gymLocation','fitnessClass'],
        where:{
            
            class_date: {
                [Op.gt]: new Date()
            }
        },
         order:[['class_date','ASC']]
    }).then(function(data){
        console.log(data);
        data=data.map(value=>value.dataValues);//ensures that data contains only valid data
        resolve(data);
    })
    .catch(function(err){
        reject('Error retrieving classes: '+err);
    });

    });
}
module.exports.getGymClassesForDate=async(dateStr)=>{
        let data = await  GymClass.findAll({
            include: ['trainer','gymLocation','fitnessClass'],
            where:{
                
                class_date: {
                    [Op.eq]: new Date(dateStr) 
                }
            },
             order:[['start_time','ASC']]
        });
        data=data.map(value=>value.dataValues);
        return data;
}
module.exports.getMemberFromEmail=(memberEmail)=>{
    return new Promise((resolve,reject)=>{
        Member.findOne({
            where: {email: memberEmail.toUpperCase()}
        })
        .then(data=>resolve(data))
        .catch(()=>reject('Error finding member'));
    });
}

module.exports.createMember=(member)=>{//perhaps pass member object straight to method
    return new Promise((resolve,reject)=>{
       bcrypt.genSalt(10).then((salt)=>{    
           bcrypt.hash(member.password,salt).then((hash)=>{
                member.password=hash;
                member.salt=salt;
                Member.create({
                    firstName:member.firstName,
                    lastName: member.lastName,
                    postalCode: member.postalCode,
                    birth_date: member.birth_date,
                    gender: member.gender,
                    email: member.email.toUpperCase(),
                    password: member.password,
                    salt: member.salt
                })
                .then((data)=>{
                    resolve({msg:'member successfully created'});
                })
                .catch(err=>reject({errMsg: 'failed to create member '+ err}));
            })
            .catch(err=>reject({errMsg: 'Error hashing password: '+err}));
        })
        .catch(err=>reject({errMsg: 'Error generating salt :' + err}))
    });
}
//finds all fitness portfolios that relate to a members email
module.exports.getFitnessPortfolioFromMember=(email)=>{
    return new Promise((resolve,reject)=>{
        this.getMemberFromEmail(email)
            .then(member=>{
                    FitnessPortfolio.findAll({
                        where:{
                            member_id: member.id
                        },
                        include: ['member']
                    })
                    .then((data)=>{
                        data=data.map(value=>value.dataValues);
                        resolve(data);
                    })
            })
            .catch(err=>reject(err));
    });
}

module.exports.getAllGymLocations = async()=>{
        const locations = await GymLocation.findAll();
        return locations;
}

module.exports.getAllExercises= async()=>{
    const exercises= await Exercise.findAll();
    return exercises;
}

