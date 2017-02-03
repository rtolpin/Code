'use strict';

angular.module('joziiAdminApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('craigslist', {
        url: '/craigslist',
        templateUrl: 'app/craigslist/craigslist.html',
        controller: 'CraigslistCtrl'
      });
  });
