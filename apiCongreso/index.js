//Constantes
const express = require('express');
const app = express();
const logger = require('morgan');
const http = require('http');
const path = require('path');
const PORT = process.env.PORT || 8080;
const bodyParser = require('body-parser');
const baseAPI = '/api/v1';
const attendees = require('./routes/attendees'); //Importamos el enrutador
const cors = require('cors');
const attendeesService = require('./routes/attendees-service');

app.use(cors());


//Configuraciones para la app
app.use(bodyParser.json());
app.use(logger('dev'));
app.use(bodyParser.urlencoded({
extended: true
}));

app.use('/attendees', attendees); //Para poder usar las operaciones definidas en attendees (router)

//Inicialización del servidor
const server = http.createServer(app);

//Conexión con la BD
attendeesService.connectDb(function (err) {
    if (err) {
        console.log("Could not connect with MongoDB - attendeesService");
        process.exit(1);
    }

    //Escucha del servidor por el puerto especificado (8080 por defecto)
    server.listen(PORT, function () {
        console.log('Server up and running on localhost:' + PORT);
    });
});
