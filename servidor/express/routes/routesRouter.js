'use strict';

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
    dataBaseRoute.getRoutes((err, result) => {
        res.send(result);
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

module.exports = router;