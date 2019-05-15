'use strict';

const express = require('express');
const router = express.Router();
const dataBase = require('../models/dataBaseUser');


router.get('/email=:email' , (req,res) => {
	
	dataBase.getUser(req.params.email, (err, result) => {
            if (err)
                res.send(err);
            else {
                res.send(result);
			}
        })
	
});

router.post('/updateHistory', (req,res) => {

	dataBase.updateHistoryRoute(JSON.parse(req.body.json),req.body.email , (err,result) => {
        if (err)
            res.send(err);
        else {
            console.log('update de rotas feito no user ' + req.body.email);
            res.send(result);
        }
	});
});

module.exports = router;

