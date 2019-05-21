'use strict';

const express = require('express');
const router = express.Router();
const dataBase = require('../models/dataBaseUser');


router.get('/email=:email', (req, res) => {

    dataBase.getUser(req.params.email, (err, result) => {
        if (err)
            res.send(err);
        else {
            res.send(result);
        }
    })

});

router.post('/updateHistory', (req, res) => {

    let routeDataBase = require('../models/databaseRoute')
    dataBase.updateHistoryRoute(req.body.json, req.body.email, (err, result) => {
        if (err)
            res.send(err);
        else {
            routeDataBase.setNewRouteRating(req.body.name, req.body.oldRating, req.body.newRating, req.body.numberOfRatings, (err, result) => {
                if (err)
                    res.send(err);
                else
                    console.log("updated route and user")
            })
        }
    });
});

module.exports = router;

