'use strict';

//Constantes
const MongoClient = require('mongodb').MongoClient; //clientes
let db; //bd
let ObjectId = require('mongodb').ObjectID;
const Attendees = function () {
};

//Configuración para la bd
Attendees.prototype.connectDb = function (callback) {
    MongoClient.connect("mongodb://admin:admin123@ds039321.mlab.com:39321/pnet-fannyvela",
        {useNewUrlParser: true},
        function (err, database) {
            if (err) {
                callback(err);
            }

   db = database.db('pnet-fannyvela').collection('attendees'); //accede a la tabla attendees

            callback(err, database);
        });
};


/*** Métodos para trabajar con la base de datos ***/

// ADD
Attendees.prototype.add = function (attendee, callback) {
    return db.insert(attendee, callback);
};

// GET ONE
Attendees.prototype.get = function (_id, callback) {
    return db.find({_id: ObjectId(_id)}).toArray(callback);
};

//GET ALL
Attendees.prototype.getAll = function (callback) {
    return db.find({}).toArray(callback); //devuelve todos los objetos
};

//UPDATE
Attendees.prototype.update = function (_id, updatedAttendee, callback) {
    delete updatedAttendee._id;
    return db.updateOne({_id: ObjectId(_id)}, {$set: updatedAttendee}, callback);};

// REMOVE ONE
Attendees.prototype.remove = function (_id, callback) {
    return db.deleteOne({_id: ObjectId(_id)}, callback);
};

//REMOVE ALL
Attendees.prototype.removeAll = function (callback) {
    return db.deleteMany({}, callback);
};

module.exports = new Attendees();
