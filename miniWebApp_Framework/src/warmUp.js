// warmUp.js
const net = require('net');
const HOST = '127.0.0.1';
const PORT = 8080;

/*const server = */
/*sock.write("Hello world!*/

const server = net.createServer((sock) => {
  /*sock.on('connection', function(){
    console.log('connection established');
  });*/
  sock.on('data', function(data){
    //console.log(data);
    const dataStr = data.toString();
    console.log(dataStr);
    //send back response data
    sock.write("<html><body><p><em>Hello</em><strong> World</strong></p></body></html>", function(success){
      if(success){ console.log("HTTP/1.1 200 OK");}
      sock.end();
      //console.log(endData);
      //console.log(endData);
    });
  });
  /*sock.on('data', function(){ });*/
  /*const res = sock.write("HTTP/1.1 200 OK\nContent-Type:'text/html'\n<em>Hello</em><strong>World</strong>", function(err){
    if(err){ console.log(err); }
    console.log('calls sock end');
    //console.log(res);
  });*/
  //console.log(res);
  /*sock.on('end', function(){*/
});

//.on('close', server.close());

server.listen(PORT, HOST);
