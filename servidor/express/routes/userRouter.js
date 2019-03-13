'use strict';

const express = require('express');
const router = express.Router();
const dataBase = require('../models/dataBaseUser');


/*router.post('/email=:email', (req, res) => {


        dataBase.getUser(req.params.email, (err, result) => {
            if (err)
                res.send(err);
            else {
				console.log("chegou")
                res.send(result);
			}
        })
    }
);*/

router.get('/email=:email' , (req,res) => {
	
	dataBase.getUser(req.params.email, (err, result) => {
            if (err)
                res.send(err);
            else {
				console.log("chegou")
                res.send(result);
			}
        })
	
})


module.exports = router;

