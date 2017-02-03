'use strict';

var app = require('../..');
import request from 'supertest';

describe('Craigslist API:', function() {

  describe('GET /api/craigslist', function() {
    var craigslists;

    beforeEach(function(done) {
      request(app)
        .get('/api/craigslist')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          craigslists = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      craigslists.should.be.instanceOf(Array);
    });

  });

});
