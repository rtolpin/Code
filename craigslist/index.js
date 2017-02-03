'use strict';

var express = require('express');
var controller = require('./craigslist.controller');
var config = require('../../config/environment');
var router = express.Router();

router.all('/*', config.appCORS);
router.get('/', controller.index);
router.get('/craigslist_search', controller.search );
router.post('/craigslist_emails', controller.sendEmails );

module.exports = router;
