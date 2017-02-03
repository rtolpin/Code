'use strict';

describe('Component: CraigslistComponent', function () {

  // load the controller's module
  beforeEach(module('joziiAdminApp'));

  var CraigslistComponent, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($componentController, $rootScope) {
    scope = $rootScope.$new();
    CraigslistComponent = $componentController('CraigslistComponent', {
      $scope: scope
    });
  }));

  it('should ...', function () {
  });
});
