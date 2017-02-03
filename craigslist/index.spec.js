'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var craigslistCtrlStub = {
  index: 'craigslistCtrl.index'
};

var routerStub = {
  get: sinon.spy()
};

// require the index with our stubbed out modules
var craigslistIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './craigslist.controller': craigslistCtrlStub
});

describe('Craigslist API Router:', function() {

  it('should return an express router instance', function() {
    craigslistIndex.should.equal(routerStub);
  });

  describe('GET /api/craigslists', function() {

    it('should route to craigslist.controller.index', function() {
      routerStub.get
        .withArgs('/', 'craigslistCtrl.index')
        .should.have.been.calledOnce;
    });

  });

});
