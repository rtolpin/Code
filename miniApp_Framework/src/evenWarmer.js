// evenWarmer.js
// create Request and Response constructors...
const net = require('net');
const fs = require('fs');
const HOST = '127.0.0.1';
const PORT = 8080;

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
    this.headers = "Location: " + url + "\r\n";
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



const server = net.createServer((sock) => {
  sock.on('data', function(data){
    const dataStr = data.toString();

    console.log(dataStr);

    const req = new Request(dataStr);
    const res = new Response(sock);
    let resStr;
    if(req.path == '/'){
      res.setHeader('Content-Type', 'text/html');
      res.send(200, "<html><head><link rel='stylesheet' type='text/css' href=/foo.css></head><body><h2 class='red'>this is a red header!</h2><p><em>Hello</em><strong> World</strong></p></body></html>");
      //res.end();
    }
    else if(req.path == '/foo.css'){
      res.setHeader('Content-Type', 'text/css');
      res.send(200, ".red { color: red; }");
      //res.end(resStr);
    }
    else if(req.path == '/test'){
      res.sendFile('html/test.html');
    }
    else if(req.path == '/img/bmo1.gif'){
      res.sendFile('img/bmo1.gif');
    }
    else{
      res.setHeader('Content-Type', 'text/plain');
      res.send(404, "uh oh... 404 page not found!");
      //res.end();
    }
  });
});

server.listen(PORT, HOST);

module.exports = {
  Request: Request,
  Response: Response
}
