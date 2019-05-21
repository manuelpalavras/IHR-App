'use strict';

const mongo = require('./dbConn');
const ObjectID = require('mongodb').ObjectID;

exports.getUser = function (email, cb) {

    mongo((db) => {
        db.collection('Users').findOne({Email: email}, (err, res) => {

            if (err || res == null)
                cb('user not found');
            else {
                cb(err, res)
            }
        })
    })

};

exports.updateHistoryRoute = function (json, email, cb) {

    json = JSON.parse(json);
    json.PontosDeInteresse = JSON.parse(json.PontosDeInteresse);

    json.PontosDeInteresse.forEach(poi => {
        poi.coordenadas.coordinates = poi.coordenadas.coordinates.replace('[', '');
        poi.coordenadas.coordinates = poi.coordenadas.coordinates.replace(']', '');
        let stringArray = poi.coordenadas.coordinates.split(",");
        let floatArray = [];
        for (let j = 0; j < stringArray.length; j++)
            floatArray.push(parseFloat(stringArray[j]))
        poi.coordenadas.coordinates = floatArray;
    });


    mongo((db) => {
        db.collection('Users').findOneAndUpdate({Email: email}, {$push: {HistoricoRotas: json}}, {new: true}, (err, res) => {
            if (err || res == null)
                cb(err);
            else {
                console.log("updated")
                cb(err,res)
            }
        })
    })

};