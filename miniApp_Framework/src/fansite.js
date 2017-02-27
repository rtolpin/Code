// fansite.js
// create your own fansite using your miniWeb framework
const App = require('./fansite.js').App;
const net = require('net');
const fs = require('fs');
const app = new AppConstr(net);

function AppConstr(net){
  this.routes = {};

  this.get = function(path, cb){
    //this.handleConnection(sock);
    if(path != '/'){
      let lastChar = path.charAt(path.length - 1);
      console.log("lastChar: ", lastChar);
      if(lastChar == '/'){ path = path.substr(0, path.length - 1); }
    }
    //if(lastChar == '/'){ path = path.substr(0, path.length - 1); }
    console.log("39: ", path);
    /*if(path == '/' || path == '/foo.css' || path == '/test' || path == '/img/bmo1.gif'
    || path == '/gone'){*/
    this.routes[path] = cb;
    //}
    /*else{
    }*/
  }

  this.logResponse = function(req, res){
    console.log(req.method + ' ' + req.path + '\n');
    console.log(res.statusCode + ' ' + 'connection has exited');
    //this.get.bind(this, req, res);
  }

  this.handleRequestData = function(sock, binaryData){
    const dataStr = binaryData.toString();

    console.log(dataStr);

    const req = new Request(dataStr);
    const res = new Response(sock);
    sock.on('close', this.logResponse.bind(this, req, res));

    console.log("22: req.path: ", req.path);
    console.log("23: this.routes[req.path]: ", this.routes[req.path]);
    if(this.routes[req.path] == undefined){
      res.send(404, 'uh oh... 404 page not found!');
    }
    if(req.headers['Host'] == undefined){
      res.send(400, "Error: Request not Valid");
    }

    if(req.path != '/'){
      let lastChar1 = req.path.charAt(req.path.length - 1);
      console.log("lastChar1: ", lastChar1);
      if(lastChar1 == '/'){ req.path = req.path.substr(0, req.path.length - 1); }
    }

    if(this.routes[req.path] != undefined || res.statusCode != 404){
      let callbackFn = this.routes[req.path];
      callbackFn(req, res);
    }

    //this.get.bind(this, req, res);
  }

  this.handleConnection = function(sock){
      //console.log("16 sock: ", sock);
      sock.on('data', this.handleRequestData.bind(this, sock));
  }

  this.server = net.createServer(this.handleConnection.bind(this));

  this.listen = function(port, host){
    this.server.listen(port, host);
  }

};



app.get('/', function(req, res){
  res.setHeader('Content-Type', 'text/html');
  res.send(200, "<html><head><link rel='stylesheet' type='text/css' href=/foo.css></head><body><h2 class='red'>Rebecca's Fansite</h2><p><strong> Hello From BMO</strong></p><img src ='http://www.geek.com/wp-content/uploads/2015/03/bmo-625x350.jpg'></img></body></html>");
  //res.sendFile('/html/index.html');
});

app.get('/about', function(req, res){
  res.setHeader('Content-Type', 'text/html');
  res.send(200, "<html><body><h1>About this magical website</h1><p>This amazing website helps you get superpowers, and become a wizard.</p></body></html>");
});

app.get('/rando', function(req, res){
  res.sendFile('html/random.html');
});

app.get('/image1.jpg', function(req, res){
  res.sendFile('img/crosscountryteam.jpg');
});

app.get('/image2.png', function(req, res){
  res.sendFile('img/Mario.png');
});

app.get('/image3.gif', function(req, res){
  res.sendFile('img/donut.gif');
});

app.get('/home', function(req, res){
  res.redirect(301, 'http://localhost:8080/');
});

app.get('/foo.css', function(req, res){
  res.setHeader('Content-Type', 'text/css');
  res.send(200, ".red { color: red; }");
});

app.get('/test', function(req, res){
  res.sendFile('html/test.html');
});

app.get('/img/bmo1.gif', function(req, res){
  res.sendFile('img/bmo1.gif');
});

app.get('/gone', function(req, res){
  res.redirect(301, 'https://foureyes.github.io/csci-ua.0480-spring2017-008/schedule.html');
});



function Request(dataStr){
  const lines = dataStr.split('\r\n');
  let lineNumber = 0;
  let lineTokens = lines[lineNumber].split(' ');
  console.log('11: ', lineTokens);
  ++lineNumber;
  const method = lineTokens[0];
  const path = lineTokens[1];
  const headers = {};
  let body = "";
  //if(!lines[lineNumber].contains(': ')){
  //}
  for(;lineNumber < lines.length; ++lineNumber){
    console.log('20: ', lines[lineNumber]);
    if(lines[lineNumber].length == 0){
      lineNumber++;
      break;
    }
    lineTokens = lines[lineNumber].split(': ');
    console.log('26: ', lineTokens);
    if(lineTokens.length != 2){ throw 'Error: Could not parse line: header'; }
    headers[lineTokens[0]] = lineTokens[1];
  }

  console.log("31: headers: ", headers);

  for(; lineNumber < lines.length; ++lineNumber){
    body += lines[lineNumber];
  }
  console.log("34: body: ", body);
  this.method = method;
  this.path = path;
  this.headers = headers;
  this.body = body;
  this.toString = function(){
    return dataStr;
  }

};

function Response(sock){
  this.sock = sock;
  this.body = "";
  this.headers = {};
  const statusCodesConversion = {200: 'OK', 404: 'Not Found', 500: 'Internal Server Error',
  400: 'Bad Request', 301: 'Moved Permanently', 302: 'Found', 303: 'See Other'};

  this.setHeader = function(name, value){
    this.headers[name] = value;
  }

  this.write = function(data){
    console.log("60 (data): ", data);
    this.sock.write(data);
  }

  this.end = function(s){
    this.sock.end(s);
  }

  this.send = function(statusCode, body){
    this.statusCode = statusCode;
    console.log(this.statusCode);
    this.body = body;
    const res = this.toString();
    this.end(res);
    //return res;
  }

  this.writeHead = function(statusCode){
    this.statusCode = statusCode;
    let head = 'HTTP/1.1 ' + this.statusCode + ' ' + statusCodesConversion[statusCode] + '\r\n';
    console.log("80(header): ", head);
    const headerKeys = Object.keys(this.headers);
    for(let i = 0; i < headerKeys.length; ++i){
      head += headerKeys[i] + ' : ' + this.headers[headerKeys[i]] + '\r\n\n';
    }
    this.write(head);
  }

  this.redirect = function(statusCode, url){
    if(statusCode == null || statusCode == undefined){
      statusCode = '301';
    }
    this.setHeader("Location", url);
    this.send(statusCode, this.body);
  }

  this.toString = function(){
    const statusCodeStr = statusCodesConversion[this.statusCode];
    let s = 'HTTP /1.1 ' + this.statusCode + ' ' + statusCodeStr + '\r\n';
    const headerKeys = Object.keys(this.headers);
    for(let i = 0; i < headerKeys.length; ++i){
      s += headerKeys[i] + ' : ' + this.headers[headerKeys[i]] + '\r\n';
    }
    s += '\n' + this.body;
    return s;
  }

  this.handleRead = function(error, data){
    console.log(data);
    if(error){
      this.statusCode = 500;
      this.send(500, 'Internal Server Error');
    }
    if(this.fileNameExt[1] == "html" || this.fileNameExt[1] == "css"){
      this.headers['Content-Type'] = 'text/' + this.fileNameExt[1];
    }
    else if(this.fileNameExt[1] == "txt"){
      this.headers['Content-Type'] = 'text/plain';
    }
    else if(this.fileNameExt[1] == "jpg" || this.fileNameExt[1] == "jpeg" ||
    this.fileNameExt[1] == "png" || this.fileNameExt[1] == "gif"){
      if(this.fileNameExt[1] == "jpg"){ this.fileNameExt[1] = "jpeg"; }
      this.headers['Content-Type'] = 'image/' + this.fileNameExt[1];
    }
    else{
      this.statusCode = 500;
      this.send(500, 'Internal Server Error');
    }
    this.writeHead(200);
    this.write(data);
    this.end();
  }

  this.sendFile = function(fileName){
    //_dirname
    const publicRoot ='./public/';
    const filePath = publicRoot + fileName;
    console.log(filePath);
    this.fileNameExt = fileName.split(".");
    let encoding;
    let file;
    if(this.fileNameExt[1] == "html" || this.fileNameExt[1] == "css" || this.fileNameExt[1] == "txt"){
      encoding = {encoding:'utf8'};
    }
    /*else{
      throw "Error: Could not Parse File Name Extension: " + fileNameExt[1];
    }*/
    if(encoding != undefined){
      file = fs.readFile(filePath, encoding, (error, data) => { this.handleRead(error, data); });
    }
    else{
      file = fs.readFile(filePath, (error, data) => { this.handleRead(error, data); });
    }
  }

};

app.listen(8080, '127.0.0.1');

module.exports = {
  App: App,
  Request: Request,
  Response: Response
}
