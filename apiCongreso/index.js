const express = require('express');
const app = express();
const logger = require('morgan');
const http = require('http');
const path = require('path');
const PORT = process.env.PORT || 8080;
const bodyParser = require('body-parser');
const baseAPI = '/api/v1';

app.use(bodyParser.json());
app.use(logger('dev'));
app.use(bodyParser.urlencoded({
extended: true
}));



const server = http.createServer(app);

server.listen(PORT, function () {
    console.log('Server up and running on localhost:' + PORT);
});
