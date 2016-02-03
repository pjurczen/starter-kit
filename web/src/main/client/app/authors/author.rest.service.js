angular.module('app.authors').factory('authorRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        search: function () {
            return $http.get(currentContextPath.get() + 'services/authors');
        }
    };
});
