//Author: Mitchell Culligan
//https://limitless-woodland-96285.herokuapp.com/ | https://git.heroku.com/limitless-woodland-96285.git
const ds = require('./dataService');
const express = require("express");
const app = express();
const jwt = require('jsonwebtoken');
const passport=require('passport');
const passportJWT=require('passport-jwt');
const onlineclass = require('./models/onlineclass');
const HTTP_PORT= process.env.PORT || 8080;


const JwtStrategy=passportJWT.Strategy;
var jwtOptions={};

jwtOptions.jwtFromRequest = passportJWT.ExtractJwt.fromAuthHeaderWithScheme("jwt");
jwtOptions.secretOrKey='1G6CvcPzx!ixKnkQxR^pvKHpDxu0!7qq2HrfLPV2jmxfCbzg!9pUJDqxHKpDRv^O';
var strategy = new JwtStrategy(jwtOptions,(jwt_payload,next)=>{
    if(jwt_payload){
        next(null,{
            id: jwt_payload.id,
            email: jwt_payload.email,
            firstName: jwt_payload.firstName,
            lastName: jwt_payload.lastName
        });//checks payload data
    }
    else
        next(null,false);
});
passport.use(strategy);//passport uses custom startegy with payload

app.use(passport.initialize());
app.use(express.json());
app.use(express.urlencoded({
    extended: true
}));
function onHTTPStart(){
    console.log("Express http server listening on port "+ HTTP_PORT);
}

function decodeToken(req){
    const authHeader=req.headers.authorization;
    let token=null
    if(authHeader){
        token=jwt.decode(authHeader.split(' ')[1]);

    }
    return token;
}

app.get("/",(req,res)=>{
    res.send("<b> Hello World</b><p>This api service is for sen-fit</p>");
});
app.post('/login',(req,res)=>{
        const user= req.body;
        ds.registerUser(user)
        .then((member)=>{
            const payload={
                id: member.id,
                email: member.email,
                firstName: member.firstName,
                lastName: member.lastName
            };
            const token = jwt.sign(payload,jwtOptions.secretOrKey);
            member.token=token;
            res.json({...member,"token":token});
        })
        .catch((err)=>{
            res.status(422).json({"errMsg": err});
        })

});
app.get('/tokenTest',passport.authenticate('jwt',{session: false}),(req,res)=>{
        
});
app.get('/members',(req,res)=>{
    ds.getAllMembers()
    .then((data)=>{
        if(data && data.length>0)
            res.json(data);
        else
            throw new Error('no members found');    

    })
    .catch((err)=> res.status(500).json({"errMsg": err}));
});
app.get("/member",(req,res)=>{
    let email = req.query.email;//retreive emaill from query params
    console.log(email);
    ds.getMemberFromEmail(email)
    .then(data=>{
        if(data){
            res.json(data);
        }
        else
        {
            throw new Error("No member found")
        }
    })
    .catch(err=>{
        res.status(500).json({errMsg: err});
    });
});

app.get('/member/:id/gymClasses',passport.authenticate('jwt',{session: false}),(req,res)=>{
    const id=req.params.id;
    ds.getGymClassesForMember(id)
    .then((classes)=>{
        if(classes.length==0)
            throw new Error("No classes found for member");
        res.json(classes);    
    })
    .catch((err)=>res.status(500).json({"errMsg":err}));
});
app.get('/member/:id/onlineClasses',passport.authenticate('jwt',{session: false}),(req,res)=>{
        const id=req.params.id;
        ds.getOnlineClassesForMember(id)
        .then((classes)=>{
            if(classes.length==0)
                throw new Error("No classes found for member");
            res.json(classes);    
        })
        .catch((err)=>res.status(500).json({"errMsg":err}));
});
app.post('/signUp',(req,res)=>{
            const member = req.body;
            ds.createMember(member)
            .then((msg)=>{
                    res.json({msg: 'Member created'});
            })
            .catch(err=>res.status(500).json({"errMsg": err}));

});
app.get('/fitnessClasses',(req,res)=>{
            ds.getAllFitnessClasses()
            .then(data=>res.json(data))
            .catch(err=>res.status(500).json({"errMsg":err}));
});

app.get('/member/:id/fitnessPortfolios',passport.authenticate('jwt',{session: false}),(req,res)=>{
        const id = req.params.id;//retrieve email from query,
        // probably will change with auth 
        ds.getFitnessPortfolioFromMember(id)
        .then((data)=>{
            if(data && data.length>0)
                res.json(data);
            else
                throw new Error("No data found");    
        })
        .catch((err)=>res.status(500).json({"errMsg": `Error retriving email for ${email}
        :   ${err}`}));
});

app.post('/fitnessPortfolio',passport.authenticate('jwt',{session: false}),(req,res)=>{
            const portfolio=req.body;
            ds.createFitnessPortfolio(portfolio)
            .then((portfolio)=>res.json({portfolio}))
            .catch((err)=>res.status(500).json({"errMsg":err}));

});
app.get('/gymClasses',passport.authenticate('jwt',{session: false}),async(req,res)=>{
    let dateStr=req.query.date;
    let member = decodeToken(req);
    let id =null;
    if(member)
        id=member.id;
    try{
        let data =null;
    data = await ds.getGymClassesWithMemberEnroll(id,dateStr);
    
    if(data && data.length>0){
            res.json(data);
    }
    else
        throw new Error('No classes found');   
    }catch(error){
        res.status(500).json({errMsg: ""+error});
    }
});

app.get('/onlineClasses',passport.authenticate('jwt',{session: false}),(req,res)=>{
    let member = decodeToken(req);
    let id =null;
    if(member)
        id=member.id;
    ds.getOnlineClassesWithMemberEnroll(id)
    .then((data)=>{
        if(data.length==0)
            throw new Error('No classes avalible');
        res.json(data);    
    })
    .catch((err)=>{
        res.status(500).json({"errMsg":err});
    });
});
app.get('/gymLocations',(req,res)=>{
    ds.getAllGymLocations()
    .then((data)=>{
        if(data && data.length>0)
            res.json(data);
        else
            throw new Error('No locations found');

    })
    .catch((err)=>{
        res.status(500).json({"errMsg":  err});
    });
});
app.get('/gymLocations/:id/trainers',async(req,res)=>{
        const id=req.params.id;
        try{
            const data = await ds.getTrainersForGymLocation(id);  
            if(data && data.length>0)
            res.json(data);
        else
            throw new Error('No trainers found'); 

        }catch(error){
            res.status(500).json({"errMsg":  error});
        }

});
app.get('/exercises',(req,res)=>{
    ds.getAllExercises()
    .then((data)=>{
        if(data && data.length>0)
            res.json({exercises: data});
        else
            throw new Error("No exercise data found");    
    })
    .catch(err=>res.status(500).json({errMsg: ''+err}));
});
app.get('/trainers',async(req,res)=>{
    const data = await ds.getAllTrainers();
   try{
    if(data && data.length>0){
        res.json(data);
    }
    else{
        throw new Error('No trainers found');
    }
    }catch(error){
        res.status(500).json({"errMsg": error});
    }
});

app.get('/covidLog',passport.authenticate('jwt',{session: false}),async(req,res)=>{
        const id = req.query.member_id;
        try{
            if(id){
                data = await ds.getCovidLogForMember(id);
                if(data)
                    res.json(data);
                else
                    throw new Error('No covid logs found');    
            }
            else
            {
                throw new Error('no member id given');
            }

        }catch(error){
            res.status(500).json({"errMsg": error});
        }
});

app.post('/gymClass/enroll',passport.authenticate('jwt',{session: false}),(req,res)=>{
    const memberClass =req.body;
            ds
            .enrollMemberGymClass(memberClass.member_id,memberClass.gymClassId)
            .then(data=>res.json(data))
            .catch(err=>res.status(500).json({"errMsg":err}))
});

app.post('/onlineClass/enroll',passport.authenticate('jwt',{session: false}),(req,res)=>{
    const memberClass= req.body;
    ds.enrollOnlineClass(memberClass.member_id,memberClass.onlineClassId)
    .then((data)=>res.json(data))
    .catch((err)=>res.status(500).json({"errMsg":err}));
});

app.get('/member/:id/trainingPlans',passport.authenticate('jwt',{session: false}),(req,res)=>{
        const id = req.params.id;
        ds.getTrainingPlansForMember(id)
        .then(data=>{
            if(data.length==0)
                throw new Error('No training plans found');
            res.json(data);    
        })
        .catch(err=>res.status.json({"errMsg":err}));
});
app.post('/trainingPlan',passport.authenticate('jwt',{session: false}),(req,res)=>{
    const plan = req.body;
    ds.enrollTrainingPlan(plan)
    .then((msg)=>res.json())
    .catch((err)=>res.status(500).json({"errMsg":err}));
});

app.post('/registerClient',(req,res)=>{
    const client=req.body;
    ds.registerClient(client)
    .then((registeredClient)=>{
        res.json(registeredClient);
    })
    .catch(err=>res.status(500).json({"errMsg":err}));
});
app.use((req, res) => {
    res.status(404).send("Page Not Found");
  });

ds.init()
.then((msg)=>{
    console.log(msg);
    
    app.listen(HTTP_PORT,onHTTPStart);
})
.catch(err=>{
    console.error(err);
});


