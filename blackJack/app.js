// app.js for homework #7 - blackjack
const express = require('express');
const app = express();
const path = require('path');

// 1. express-static
app.use(express.static(path.join(__dirname, 'public')));
console.log(path.join(__dirname, 'public'));


//app.set('', path.join(__dirname, ''));

app.use(function(req, res, next){
  next();
});

app.listen(3000);
