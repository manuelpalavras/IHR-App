'use strict';

const mongo = require('./dbConn');

exports.getCities = function (cb) {
    mongo((db) => {
        db.collection('Cidades').find().toArray((err, result) => {
            if (err)
                cb('routes not found');
            else {
                cb(err, result)
            }
        })
    })
};

exports.getTypesOfRoutesByCity = function (rota, cb) {
    mongo((db) => {
        db.collection('Rotas').distinct('Tipo', {Nome: rota}, (err, result) => {
            if (err)
                cb('tipos not found');
            else {
                cb(err, result)
            }

        })
    })
};

exports.getDifficultyByCity = function (rota, cb) {

    mongo((db) => {
        db.collection('Rotas').distinct('Dificuldade', {Nome: rota}, (err, result) => {
            if (err)
                cb('dificuldade not found');
            else {
                cb(err, result)
            }
        })
    })
};

exports.getClassificationByCity = function (rota, cb) {

    mongo((db) => {
        db.collection('Rotas').distinct('Classificacao', {Nome: rota}, (err, result) => {
            if (err)
                cb('Classificacao not found');
            else {
                cb(err, result)
            }
        })
    })
};