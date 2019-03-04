'use strict';

const mongo = require('./dbConn');
const fs = require('fs');

exports.getPoI = function (cb) {

    mongo((db) => {
        db.collection('Rotas').distinct("PoI.Nome", (err, res) => {
            if (err)
                cb('PoI not found');
            else
                cb(err, res)
        })
    })


};

exports.getJSONFile = function (nome, cb) {
    let content;
    fs.readFile(`models/json/${nome}`, 'utf-8', (err, data) => {
        if (err)
            cb(err);
        else {
            //let buf = Buffer.from(data);
            // console.log(data);
            content = JSON.parse(data);
            cb(null, data);
        }
    });
};

exports.clearJSON = function (cb) {
    let json = "{}";
    fs.writeFile('models/json/locationInfo.json', json, 'utf8', (err, res) => {
        if (err)
            res.send(err);
    });
    cb(null, null)
};
//simula um post à base de dados quando tivermos utlizadores para obter um historico de localizações

exports.postLocation = function (latitude, longitude, cb) {

    const coordinates = {
        ponto: [latitude, longitude]
    };

    let json = JSON.stringify(coordinates);

    fs.writeFile('models/json/locationInfo.json', json, 'utf8', (err, res) => {
        if (err)
            res.send(err);
    });
    cb(null, null);
};

