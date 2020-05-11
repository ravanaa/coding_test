var express = require('express');
var body_parser = require('body-parser');
var logger = require('morgan');
var path = require('path');
const http = require('http');
const httpProxy = require('http-proxy');

const app = express();
const http_server = http.createServer(app);

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

const apiProxy = httpProxy.createProxyServer({
    target:{
        host: 'localhost',
        port: 8080
    }
});

app.get('/',function(req,res){
    res.sendFile(path.join(__dirname + '/index.html'));
});

app.all('/library/*',function(req,res,next){
    console.log('recieved the request for backend')
    apiProxy.web(req,res);
})

// var server = app.listen(6060,function(){
//     var host = server.address().address;
//     var port = server.address().port;
    
//     console.log('Server running  on %s and port %d',host,port);
// });

http_server.listen(6060,'0.0.0.0',(error)=>{
    if (error) {
        console.error(error);
    } else {
        console.info(`==> 🌎  Listening on port 6060 Open up http://localhost:6060/ in your browser.`);
    }
});
