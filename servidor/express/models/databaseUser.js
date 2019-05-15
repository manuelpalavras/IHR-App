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

    JSON.parse(json["Pontos de Interesse"]);
    JSON.parse(json["Pontos de Interesse"]["coordenadas"]["coordinates"]);


    mongo((db) => {
        db.collection('Users').findOneAndUpdate({Email: email}, {$push: {HistoricoRotas: json}}, {new: true}, (err, res) => {
            if (err || res == null)
                cb(err);
            else {
                console.log("updated")
            }
        })
    })

};