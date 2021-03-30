//Author: Mitchell Culligan
//https://limitless-woodland-96285.herokuapp.com/ | https://git.heroku.com/limitless-woodland-96285.git
const ds = require('./dataService');
const express = require("express");
const app = express();
const HTTP_PORT= process.env.PORT || 8080;

app.use(express.json());
app.use(express.urlencoded({
    extended: true
}));
function onHTTPStart(){
    console.log("Express http server listening on port "+ HTTP_PORT);
}

app.get("/",(req,res)=>{
    res.send("<b> Hello World</b><p>This api service is for sen-fit</p>");
});

app.get('/members',(req,res)=>{
    ds.getAllMembers()
    .then((data)=>{
        if(data && data.length>0)
            res.json({members: data});
        else
            throw new Error('no members found');    

    })
    .catch((err)=> res.status(500).json({errMsg: "Error: "+err}));
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

app.post('/member',(req,res)=>{
            const member = req.body;
            ds.createMember(member)
            .then((msg)=>{
                    res.json({msg: 'Member created'});
            })
            .catch(err=>res.status(500).json(err));

});

app.get('/fitnessPortfolio',(req,res)=>{
        const email = req.query.email;//retrieve email from query,
        // probably will change with auth 
        ds.getFitnessPortfolioFromMember(email)
        .then((data)=>{
            if(data && data.length>0)
                res.json(data);
            else
                throw new Error("No data found");    
        })
        .catch((err)=>res.status(500).json({errMsg: `Error retriving email for ${email}
        :   ${err}`}));
});
app.get('/gymClasses',async(req,res)=>{
    let dateStr=req.query.date;
    try{
        let data =null;
    if(dateStr)
        data= await ds.getGymClassesForDate(dateStr);
    else
        data = await ds.getUpcomingGymClasses();
    
    if(data && data.length>0)
                res.json(data);
    else
        throw new Error('No classes found');   
    }catch(error){
        res.status(500).json({errMsg: ""+error});
    }
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
        res.status(500).json({errMsg: ''+ err});
    });
});
app.get('/gymLocations/:id/trainers',async(req,res)=>{
        const id=req.params.id;
        try{
            const data = await ds.getTrainersForGymLocation(id);  
            if(data && data.length>0)
            res.json({locations: data});
        else
            throw new Error('No trainers found'); 

        }catch(error){
            res.status(500).json({errMsg: ''+ error});
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
        res.json({trainers: data});
    }
    else{
        throw new Error('No trainers found');
    }
    }catch(error){
        res.status(500).json({errMsg: ""+error});
    }
});

app.get('/covidLog',async(req,res)=>{
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
            res.status(500).json({errMsg: ""+error});
        }
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


