'use strict';

const MongoClient = require('mongodb').MongoClient;
const url = 'mongodb://localhost:27017';
const assert = require('assert');
let db;

// Use connect method to connect to the server
MongoClient.connect(url, {poolSize: 10, useNewUrlParser: true}, function (err, client) {
    assert.equal(null, err);
    console.log("MongoBD connected successfully to server");
    db = client.db('IHR');
});

module.exports = function (cb) {
    cb(db)
};