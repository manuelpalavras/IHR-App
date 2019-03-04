'use strict';

const express = require('express');
const router = express.Router();
const dataBase = require('../models/dataBaseUser');


router.post('/email=:email&password=:password', (req, res) => {


        dataBase.getUser(req.params.email, (err, result) => {
            if (err)
                res.send(err);
            else
                res.send(result);
        })
    }
);


module.exports = router;

