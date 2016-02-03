angular.module('app.books', ['ngRoute', 'app.main', 'ui.bootstrap', 'flash']).config(function ($routeProvider) {
    'use strict';

    $routeProvider.when('/books/book-list', {
        templateUrl: 'books/list/book-list.html',
        controller: 'BookSearchController'
    });
});