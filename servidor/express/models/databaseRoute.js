'use strict';

const mongo = require('./dbConn');
const ObjectID = require('mongodb').ObjectID;

exports.getRouteByID = function (id, cb) {

    mongo((db) => {
        db.collection('Rotas').findOne({_id: new ObjectID(id)}, (err, res) => {
            if (err || res == null)
                cb('route not found');
            else {
                cb(err, res)

            }

        })
    })
};

exports.getCityRoutes = function (name, cb) {

    mongo((db) => {
        db.collection('Rotas').find({Cidade: name}).toArray((err, result) => {
            if (err)
                cb('routes not found');
            else
                cb(err, result)
        })
    })
};

exports.getRoutes = function (cb) {
    mongo((db) => {
        db.collection('Rotas').find().toArray((err, result) => {
            if (err)
                cb('routes not found');
            else
                cb(err, result)
        })
    })

};

exports.getFilteredRoutes = function (nome, tipos, classificacao, dificuldade, cb) {
    let queryOr=[];
    queryOr[0]={ Cidade: nome }
    queryOr[1]={PoI: {$elemMatch: {Nome: nome}}}

    for (let i = 0; i < classificacao.length; i++) {
        queryOr.push( {Classificacao:{$gte:classificacao[i+2] - 0.5, $lte: classificacao[i+2] - (-0.4)}})
    }



    mongo((db) => {
        // let queryNome =[ { Cidade: nome },  {PoI: {$elemMatch: {Nome: nome}}} ] ;
        let queryS
        if (tipos[0] === "null" && classificacao[0] === "null" && dificuldade[0] === "null") {
            queryS = {
                $or:[ { Cidade: nome },  {PoI: {$elemMatch: {Nome: nome}}} ]
            };
        }
        else if (tipos[0] === "null" && classificacao[0] === "null") {
            queryS = {
                $or:[ { Cidade: nome },  {PoI: {$elemMatch: {Nome: nome}}} ],
                Dificuldade: {$in: dificuldade}
            };
        }
        else if (classificacao[0] === "null" && dificuldade[0] === "null") {
            queryS = {
                $or:[ { Cidade: nome },  {PoI: {$elemMatch: {Nome: nome}}} ],
                Tipo: {$in: tipos}
            };
        }
        else if (tipos[0] === "null" && dificuldade[0] === "null") {
            queryS = {
                $or:queryOr
            };

        }
        else if (tipos[0] === "null") {
            queryS = {
                Dificuldade: {$in: dificuldade},
                $or:queryOr
            };
        }
        else if (dificuldade[0] === "null") {
            queryS = {
                Tipo: {$in: tipos},
                $or:queryOr
            };
        }
        else if (classificacao[0] === "null") {
            queryS = {
                $or:[ { Cidade: nome },  {PoI: {$elemMatch: {Nome: nome}}} ],
                Tipo: {$in: tipos},
                Dificuldade: {$in: dificuldade}
            };
        }
        else{
            queryS = {
                Tipo: {$in: tipos},
                Dificuldade: {$in: dificuldade},
                $or:queryOr
            };
        }

        db.collection("Rotas").find(queryS).toArray((err, res) => {
            if (err || res == null) {
                cb('route not found');
            }
            else {
                cb(err, res)

            }


        })
    })
};

exports.getRoutesOfPoI = function (PoI, cb) {

    mongo((db) => {
        db.collection('Rotas').find({PoI: {$elemMatch: {Nome: `${PoI}`}}}).toArray((err, result) => {
            if (err)
                cb('Routes not found');
            else {
                cb(err, result)
            }
        })
    })
};