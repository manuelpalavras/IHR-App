'use strict';

const express = require('express');
const router = express.Router();
const dataBaseRoute = require('../models/databaseRoute');
const dataBaseCity = require('../models/databaseCity');


router.get('/city/:cityName', (req, res) => {
    dataBaseRoute.getCityRoutes(req.params.cityName, (err, result) => {
        res.send(result);
    })
});

router.get('/cities', ((req, res) => {
    dataBaseCity.getCities((err, result) => {
        res.send(result);
    })
}));

module.exports = router;
