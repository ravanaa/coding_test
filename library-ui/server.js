var express = require('express');
var body_parser = require('body-parser');
var logger = require('morgan');
var path = require('path');
const http = require('http');
// const httpProxy = require('http-proxy');

const app = express();
const http_server = http.createServer(app);

app.use(body_parser.urlencoded({ extended: true }));
app.use(body_parser.json());

app.use(function (req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST');
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With, content-type, Authorization');
    next();
});

app.use(logger('dev'));

app.use(express.static(__dirname + '/app'));

// const apiProxy = httpProxy.createProxyServer({
//     target: {
//         host: 'localhost',
//         port: 8080
//     }
// });

app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname + '/index.html'));
});

// app.all('/library/*',function(req,res,next){
//     console.log('recieved the request for backend');
//     apiProxy.web(req,res);
// })

app.use('/library/*', (req, res) => {

    console.log("recieved request and processing it: " + req.originalUrl);
    const uri = req.originalUrl;
    if (req.method === 'POST') {
        console.log("Sending post reqeuest...");
        var req_body = JSON.stringify(req.body);
        sendPostRequest(req.originalUrl,res,req_body);
        console.log("retrieving the data");
    }
    if (req.method === 'GET') {
        console.log("Sending Get request...")
        sendGetRequest(uri,res);
    }
   
})




// var server = app.listen(6060,function(){
//     var host = server.address().address;
//     var port = server.address().port;

//     console.log('Server running  on %s and port %d',host,port);
// });

http_server.listen(6060, '0.0.0.0', (error) => {
    if (error) {
        console.error(error);
    } else {
        console.info(`==> 🌎  Listening on port 6060 Open up http://localhost:6060/ in your browser.`);
    }
});

function sendPostRequest(url, res, post_body) {
    console.log("Sending post request to : " + url);
    const mydata = JSON.stringify({
        todo: 'Buy the milk'
    })
    const options = {
        hostname: 'localhost',
        port: 8080,
        path: url,
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Content-Length': post_body.length
        }
    }

    const _req = http.request(options, resp => {
        console.log(`statusCode: ${resp.statusCode}`)

        var data = '';
        resp.on('data', (chunk) => {
            data += chunk;
        });
        resp.on('end', () => {
            // console.log(JSON.parse(data).explanation);
            console.log(data);
            res.write(data);
            res.end();
        });
    });

    _req.write(post_body)
    _req.end();

};

function sendGetRequest(url,res){
    var get_url = 'http://localhost:8080' + url;
    http.get(get_url, resp => {
        var data = '';
        resp.on('data', (chunk) => {
            data += chunk;
        });
        resp.on('end', () => {
            console.log(data);
            res.write(data);
            res.end();
        })
    })
}

