//Author Mitchell Culligan
//Sen-fit data service
const MAX_GYM_POP=50;//max amount of people allowed for a class
const {sequelize, 
    Member, 
    GymClass,
    OnlineClass,
    Trainer,
    Exercise,
    TrainingPlan,
    FitnessClass,
    CovidLog,
    FitnessPortfolio,
    UnregisteredClient,
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
        where:{
            
            class_date: {
                [Op.gt]: new Date()
            }
        },
         order:[['class_date','ASC']]
    }).then(function(data){
        
            data=data.map(value=>value.dataValues);//ensures that data contains only valid data
            resolve(data);
    })
    .catch(function(err){
        reject('Error retrieving classes: '+err);
    });

    });
}

module.exports.getGymClassesWithMemberEnroll=async(id,dateStr)=>{
    let data=null;
    if(dateStr)
        data = await this.getGymClassesForDate(dateStr)
    else
        data = await this.getUpcomingGymClasses();
    try{    
        const member = await Member.findOne({where:{id:id}});
        let memberClasses= await member.getGymClasses();
        memberClasses=memberClasses.map(value=>value.dataValues);
        memberClasses=memberClasses.map(val=>val.id);
        data=data.map(value=>{
            value.enrolled=memberClasses.includes(value.id);
            return value;
        });
    }catch(error){
        console.error(error);
    }
    return data;

}
module.exports.getGymClassesForDate=(dateStr)=>{
    return new Promise((resolve,reject)=>{
        GymClass.findAll({
            include: ['trainer','gymLocation','fitnessClass'],
            where:{
                
                class_date: {
                    [Op.eq]: new Date(dateStr)
                }
            },
             order:[['start_time','ASC']]
        }).then(function(data){
        
            data=data.map(value=>value.dataValues);//ensures that data contains only valid data
            resolve(data);
        })
        .catch(function(err){
            reject('Error retrieving classes: '+err);
        });
    
        });
}
module.exports.getOnlineClasses=()=>{
    return new Promise((resolve,reject)=>{
        OnlineClass.findAll({
            where:{
                
                classDate: {
                    [Op.gt]: new Date()
                }
            },
             order:[['classDate','ASC']]
        }).then(function(data){
            
            data=data.map(value=>value.dataValues);//ensures that data contains only valid data
            resolve(data);
        })
        .catch(function(err){
            reject('Error retrieving classes: '+err);
        });
    
        });
}
module.exports.getOnlineClassesWithMemberEnroll=async(id)=>{
    let data = await this.getOnlineClasses();
   try{
    const member= await Member.findOne({where:{id:id}});
    let memberClasses = await member.getOnlineClasses();
    memberClasses=memberClasses.map(value=>value.dataValues);
    memberClasses=memberClasses.map(val=>val.id);
    data=data.map(value=>{
        value.enrolled=memberClasses.includes(value.id);
        return value;
    });
    }catch(error){
        console.error(error);
    }

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
                .catch(err=>reject(err));
            })
            .catch(err=>reject({errMsg: 'Error hashing password: '+err}));
        })
        .catch(err=>reject({errMsg: 'Error generating salt :' + err}))
    });
}
//finds all fitness portfolios that relate to a members email
module.exports.getFitnessPortfolioFromMember=(id)=>{
    return new Promise((resolve,reject)=>{
        
        FitnessPortfolio.findAll({
                where:{
                    member_id: id
                    },
                
                })
                .then((data)=>{
                    data=data.map(value=>value.dataValues);
                    resolve(data);
                })
                .catch(err=>reject(err));
       
    });
}

    module.exports.getAllFitnessClasses= async()=>{
        let classes = await FitnessClass.findAll();
        classes=classes.map(value=>value.dataValues);
        return classes;

    }

module.exports.getAllGymLocations = async()=>{
        const locations = await GymLocation.findAll();
        return locations;
}

module.exports.getAllExercises= async()=>{
    const exercises= await Exercise.findAll();
    return exercises;
}

module.exports.getAllTrainers=async()=>{
    const trainers = await Trainer.findAll();
    return trainers;
}

module.exports.getCovidLogForMember= async(memberId)=>{
            await CovidLog.max('date_logged',{where: 
                {
                    member_id: memberId
                }
                });
}


module.exports.getTrainersForGymLocation=async(gymId)=>{
        const trainers = await Trainer.findAll({
            where:{
                gymLocationId: gymId
            }
        });
        return trainers;
}

module.exports.registerUser = (user)=>{
    return new Promise((resolve,reject)=>{

        Member.findOne({
            where:{
                email: user.email.toUpperCase()
            }
        })
        .then(member=>{
                bcrypt.compare(user.password,member.password).then((res)=>{
                    if(res)//check if member password matches
                        resolve(member.dataValues);
                    else
                        reject("Incorrect password for member "+ user.email);    
                })
        })
        .catch((err)=> reject("Unable to find member "+ user.email));

    });
}

module.exports.enrollMemberGymClass = async(memberId,gymClassId)=>{
    const member = await Member.findOne({where: {id:memberId}});
    const gymClass = await GymClass.findOne({
        where:{
            [Op.and]:[
                {id:gymClassId},
                {isFull:false}
            ]
        }
    });
    if(!gymClass) throw new Error('Member could not be enrolled');
    await member.addGymClass(gymClass,{through: 'Member_GymClass'});
    const classPop= await GymClass.count({
        include:[{model: Member}],
        where:{
            id: gymClassId
        }
    });
    
    if(classPop==MAX_GYM_POP){
        gymClass.isFull=true;
        await gymClass.save();
                
    }
    return gymClass;

}


module.exports.enrollOnlineClass=async(memberId,classId)=>{
   
        const member = await Member.findOne({where: {id:memberId}});
        const onlineClass = await OnlineClass.findOne({
            where:{
             
                    id: classId    
            }
        });
        if(!onlineClass) throw new Error('Member could not be enrolled');
        await member.addOnlineClass(onlineClass,{through: 'Member_OnlineClass'});

        return onlineClass;

}
module.exports.getGymClassesForMember= (memberId)=>{
    return new Promise((resolve,reject)=>{
            Member.findOne({
                where:{
                    id:memberId
                }
            })
            .then((member)=>{
                    member.getGymClasses()
                    .then((data)=>{
                        data=data.map(value=>value.dataValues);
                        resolve(data);
                    })
                    .catch(err=>reject(err));
            })
            .catch(err=> reject('Unable to find member'));
    });
}

module.exports.getOnlineClassesForMember=(memberId)=>{
    return new Promise((resolve,reject)=>{
        Member.findOne({
            where:{
                id:memberId
            }
        })
        .then((member)=>{
                member.getOnlineClasses()
                .then((data)=>{
                    data=data.map(value=>value.dataValues);
                    resolve(data);
                })
                .catch(err=>reject(err));
        })
        .catch(err=> reject('Unable to find member'));
    });
}
module.exports.enrollTrainingPlan=(trainingPlan)=>{
    return new Promise((resolve,reject)=>{
    TrainingPlan.create(trainingPlan)
    .then((plan)=>resolve(plan))
    .catch(err=>reject(err));
    });
}

module.exports.getTrainingPlansForMember=async(id)=>{
   let data = await TrainingPlan.findAll({
        include: ['fitnessPortfolios'],
        where:{
        member_id: id
        }
    });
    data=data.map(value=>value.dataValues);
    return data;
}
module.exports.createFitnessPortfolio=async(portfolio)=>{
        let p  =  await FitnessPortfolio.create(portfolio);
        return p;
}

module.exports.registerClient = (client)=>{
    return new Promise((resolve,reject)=>{
            UnregisteredClient
            .create(client)
            .then(registerClient=>resolve(registerClient))
            .catch((err)=>reject(err));
    });
}