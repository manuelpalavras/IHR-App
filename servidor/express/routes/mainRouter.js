'use strict';

const express = require('express');
const router = express.Router();
const dataBase = require('../models/dataBase');

router.get('/PoI', (req, res) => {

    dataBase.getPoI((err, result) => {
        res.send(result);
    })
});

module.exports = router;
