/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/craigslist              ->  index
 */


'use strict';
var _ = require('lodash');
var request = require('request');
var cheerio = require('cheerio');
var Promise = require('bluebird');
var nodemailer = require('nodemailer');
var Bluebird = require('bluebird');
var handlebarEngine = require('express-handlebars');
var handlebarMailer = require('nodemailer-express-handlebars');
var templatePath = process.env.NODE_ENV === 'development' ? 'client/assets/emails/' : 'public/assets/emails/';
var imagePath = 'https://s3.amazonaws.com/jozii/assets/email_img/';

var plainTransporter = nodemailer.createTransport({
  service: 'Gmail',
  auth: {
    user: 'info@jozii.com',
    pass: 'carter11'
  }
});
var handlebarTransporter = nodemailer.createTransport({
  service: 'Gmail',
  auth: {
    user: 'info@jozii.com',
    pass: 'carter11'
  }
});

handlebarTransporter.use('compile', handlebarMailer({
  viewEngine: {
    // extname: '.hbs',
    layoutsDir: process.env.DOMAIN,
    partialsDir : process.env.DOMAIN
  },
  viewPath: templatePath
}));

var sendHandlebarPromisified = Bluebird.promisify(handlebarTransporter.sendMail, handlebarTransporter);
var sendPlainPromisified = Bluebird.promisify(plainTransporter.sendMail, plainTransporter);

// Gets a list of Craiglists
exports.index = function(req, res) {
  res.json([]);
}

/**
 * Scrapes from Craigslist website using requests & cheerio
 * 1.Gets data returned from Craigslist job posting links, puts them into listing objects
 * 2.Then, makes requests to email reply links, gets email address & adds it back into the listing object as 'email'
 * 3.Stores updated listings - then, once finished, returns listings set
 */
exports.search = function(req, res){
  var links = [], linkPromises = [], emailPromises = [];
  req.query.pageResults*= 50;
  var url = 'https://newyork.craigslist.org/search/jjj?s=' + req.query.pageResults;
  var recaptCount = 0;

  var options = {
    'url': url,
    'type': 'GET',
    'headers': {
      'User-Agent': 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36'
    }
  };

  request(options, function(error, response, html){
      if(error){console.log(error); }
      if(_.isEmpty(html)){return; }
      var $ = cheerio.load(html);

      $('p.row > a').filter(function(index){
        return index < 50;
      })
      .each(function(it, element){
        console.log("goes into each loop");
        var link = $(this).attr('href');
        links.push(link);
      });

    console.log(links);
    links.forEach(function(link, i){
      var post = processLinks(link, i, options);
      linkPromises.push(post);
    });
    Promise.all(linkPromises).then(function(linkResults){
      _.remove(linkResults, function(i){
        return _.isEmpty(i);
      });

      linkResults.forEach(function(posting, i){
        var email = processEmails(posting, i, options, recaptCount);
        emailPromises.push(email);
      });

      Promise.all(emailPromises).then(function(jobResults){
        _.remove(jobResults, function(i){
          return _.isEmpty(i);
        });
        jobResults.forEach(function(listing, i){
          if(listing.email == 'Blocked by ReCaptcha'){++recaptCount;}
        });

        return res.status(200).json({jobResults: jobResults, recaptCount: recaptCount});
      });
    });
  });

};

/**
* Processes Craigslist job posting and returns information from page
* @param: {String} link - link to be visited
* @param: {int} index - index of link from array of links
*/
function processLinks(link, index, options){
  return new Promise(function(resolve, reject){
    var url = 'https://newyork.craigslist.org' + link;
    options.url = url;

    request(options, function(err, response, body){
        if(_.isEmpty(body)){return; }
        var $ = cheerio.load(body);
        var listing = {
          title: $('h2.postingtitle').text().replace("favorite this post", "").replace("hide this posting", "")
                .replace("restore this posting", "").trim(),
          url: url,
          location: $("a[href$= '/" + url.split('/')[3] + "/']").text().trim().toUpperCase(),
          email: $('#replylink').attr('href'),
          date: $('time.timeago').filter(function(index){return index == 0;}).text().trim(),
          description: $('#postingbody').text().trim().substr(0, 100)
        };
        if(!_.isEmpty(listing)){
          resolve(listing);
        } else { reject(); }
      });
  });
};

/**
* Visits the email reply link contained in the job posting & extracts the email address
* @param {Object} posting - the object containing data from the Craigslist job posting
* @param {int} i - the index of the posting from the output array
*/
function processEmails(posting, i, options, recaptCount){

  if(posting.email){
    return new Promise(function(resolve, reject){
      var replyLink = 'https://newyork.craigslist.org' + posting.email;
      options.url = replyLink;

      request(options, function(err, response, body){
          if(_.isEmpty(body)){return; }
          var $ = cheerio.load(body);
          var emailString;
          if($('div.captcha').length){
            emailString = 'Blocked by ReCaptcha';
          }
          else{
            emailString = $("a.mailapp").text().trim();
          }
          posting.email = emailString;
          resolve(posting);
      });
    });
  }
};

/**
 * Sends an email to each email address in req.body.emailParamsArray
 * @param: {Object} req.body.emailParamsArray objects that contains Craigslist poster's email address & link to their posting
 */
exports.sendEmails = function(req, res){
  if(req.body.emailParamsArray){
    _.forEach(req.body.emailParamsArray, function(obj, it){
        var mailOptions = {
          to: obj.email,
          from: 'Jozii Team <info@jozii.com>',
          subject: "Create a Free Job Posting on Jozii",
          template: 'craigslist-employer',
          context: {
            jobPosting: obj.uri,
            imageSrc: imagePath
          }
        };

        return handlebarTransporter.sendMail(mailOptions, function(info, err){
          console.log(info);
          if(err){console.log(err);}
          if(it == req.body.emailParamsArray.length - 1){
            return res.status(200).send();   //send back response through either send or json, so response is not hanging
          }
        });
    });
  }
  else if(req.body.inputEmails){
    _.forEach(req.body.inputEmails, function(email, it){
      var mailOptions = {
        to: email,
        from: 'Jozii Team <info@jozii.com>',
        subject: "Create a Free Job Posting on Jozii",
        template: 'craigslist-employer',
        context: {
          imageSrc: imagePath
        }
      };

      return handlebarTransporter.sendMail(mailOptions, function(info, err){
        console.log(info);
        if(err){console.log(err);}
        if(it == req.body.inputEmails.length - 1){
          return res.status(200).json(info); //send back response through either send or json, so response is not hanging
        }
      });
    });
  }
};
