'use strict';

//Constantes
const express = require('express');
const router = express.Router();

 //Importamos las conexiones con la BD
const attendeesService = require('./attendees-service');

/******************   OPERACIONES  ******************/

/*** GET ***/

//Recupera TODOS los asistentes
router.get('/', function (req, res) {
  attendeesService.getAll((err, attendees) => {
    if (err) {
      res.status(500).send({ msg: err });
    } else if (attendees === null){
        res.status(500).send({ msg: "attendees null" });
    } else if (attendees !== null) {
        res.status(200).send(attendees);
    }
  });
});

//Recupera UN asistente
router.get('/:_id', function (req, res) {
    let _id = req.params._id;
    attendeesService.get(_id, (err, attendee) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (attendee === null){
                res.status(500).send({
                    msg: "attendee null"
                });
            } else if (attendee !== null) {
                res.status(200).send(attendee);
            }
        }
    );
});

/*** POST ***/

//Para crear nuevo asistente
router.post('/', function (req, res) {
    let attendee = req.body;
    attendeesService.add(attendee, (err, attendee) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (attendee !== null) {
                res.send({
                    msg: 'Attendee created!'
                });
            }
        }
    );
});

/*** PUT ***/

//Actualiza la info de UN asistente
router.put('/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedAttendee = req.body;
    attendeesService.update(_id, updatedAttendee, (err, numUpdates) => {
        if (err || numUpdates === 0) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Attendee updated!'
            });
        }
    });
});

/*** DELETE ***/

//Elimina a TODOS los asistentes
router.delete('/', function (req, res) {
    attendeesService.removeAll((err) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Attendees deleted!'
            });
        }
    });
});

//Elimina UN asistente
router.delete('/:_id', function (req, res) {
    let _id = req.params._id;
    attendeesService.remove(_id, (err) => {
        if (err) {
            res.status(404).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Attendee deleted!'
            });
        }
    });
});

//Exporta el router
module.exports = router;
