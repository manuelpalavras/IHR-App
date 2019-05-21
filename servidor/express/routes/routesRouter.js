'use strict';
const path = require('path');
const express = require('express');
const router = express.Router();
const dataBaseRoute = require('../models/databaseRoute');
const dataBaseCity = require('../models/databaseCity');


router.get('/route/:routeid', (req, res) => {
    dataBaseRoute.getRouteByID(req.params.routeid, (err, result) => {
        res.send(result);
    })
});

router.get('/routes', (req, res) => {
    console.log("chegou")
    dataBaseRoute.getRoutes((err, listOfResults) => {
        res.send(listOfResults);
    })
});

router.get('/routes/PoI/:Name', (req, res) => {

    dataBaseRoute.getRoutesOfPoI(req.params.Name, (err, result) => {
        res.send(result);
    })
});

router.get('/types/:Rota', (req, res) => {
    dataBaseCity.getTypesOfRoutesByCity(req.params.Rota, (err, result) => {
        res.send(result);
    })
});

router.get('/difficulty/:Rota', (req, res) => {
    dataBaseCity.getDifficultyByCity(req.params.Rota, (err, result) => {
        res.send(result);
    })
});

router.get('/classification/:Rota', (req, res) => {
    dataBaseCity.getClassificationByCity(req.params.Rota, (err, result) => {
        res.send(result);
    })
});

router.get('/route/image/:name', (req, res) => {


    res.send(JSON.stringify("/image/Imagens/" + req.params.name))

});

router.post('/updateRating', (req, res) => {

    console.log(req.body)
    dataBaseRoute.setNewRouteRating(req.body.name, req.body.oldRating, req.body.newRating, req.body.numberOfRatings, (err, result) => {
        if (err || res == null) {
            console.log(err)
        }
        else {
            console.log("Route Rating updated")
        }
    })
});


module.exports = router;