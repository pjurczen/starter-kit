angular.module('app.authors').controller('AuthorSearchController', function ($scope, $window, $location, authorService, Flash) {
    'use strict';

    $scope.authors = [];

    $scope.init = function () {
        authorService.search().then(function (response) {
            angular.copy(response.data, $scope.authors);
        }, function () {
            Flash.create('danger', 'Failed to load authors!', 'custom-class');
        }); 
    };
    
    angular.element().ready($scope.init());
});
