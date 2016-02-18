var express = require('express');
var bodyParser     =        require("body-parser");

var app = express();
//Here we are configuring express to use body-parser as middle-ware.
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json()); 

var args = process.argv;
var port = parseInt(args[2]);

var port = (process.env.VCAP_APP_PORT || (port || 9000));
var host = (process.env.VCAP_APP_HOST || 'localhost');

app.get('/realtime', function (req, res) {
  res.send('9000 Hello world to test realtime deployment!! Server: '+ port +', Port: '+ host);
});

app.get('/realtime/test', function (req, res) {
  res.send('test 8000 Hello world to test realtime deployment!! Server: '+ port +', Port: '+ host);
});


app.get('/realtime/push', function (req, res) {
  console.log(req.query);
  res.send('get');
})

app.post('/realtime/push', function (req, res) {
	console.log("post");
console.log(req.body);
//console.log(req.data);
  res.send('post');
})

app.get('/realtime/path/test2', function (req, res) {
  res.send('test2 8000 Hello world to test realtime deployment!! Server: '+ port +', Port: '+ host);
});

console.log("Listening on " + host + ":" + port);
console.log("process.env.VCAP_APP_PORT " + process.env.VCAP_APP_PORT);
console.log("process.env.VCAP_APP_HOST " + process.env.VCAP_APP_HOST);
app.listen(port, host);
