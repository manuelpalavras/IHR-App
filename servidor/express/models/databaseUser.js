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