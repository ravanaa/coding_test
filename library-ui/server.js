var express = require('express');
var body_parser = require('body-parser');
var logger = require('morgan');
var path = require('path');


var app = express();

app.use(body_parser.urlencoded({extended:true}));
app.use(body_parser.json());

app.use(function(req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST');
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With, content-type, Authorization');
    next();
});

app.use(logger('dev'));

app.use(express.static(__dirname+'/app'));

app.get('/',function(req,res){
    res.sendFile(path.join(__dirname + '/index.html'));
});

var server = app.listen(6060,function(){
    var host = server.address().address;
    var port = server.address().port;
    
    console.log('Server running  on %s and port %d',host,port);
});
