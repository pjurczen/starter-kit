angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [];
    $scope.prefix = '';

    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };

    $scope.search = function () {
        bookService.search($scope.prefix).then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function () {
            Flash.create('danger', 'Failed to load books!', 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Book successfully deleted.', 'custom-class');
        });
    };
    
    $scope.addBook = function() {
		$modal.open({
			templateUrl : 'books/modal/book-modal.html',
			controller : 'BookModalController',
			size : 'lg',
			resolve : {
				book : function() {
					return {
						id : null,
						title : '',
						authors : []
					};
				}
			}
		}).result.then(function(response) {
			Flash.create('success', 'Book "' + response.title + '" added successfully!', 'custom-class');
			$scope.books.push(response);
		}, function() {
			Flash.create('danger', 'Failed to add book!', 'custom-class');
		});
	};
	
	$scope.updateBook = function(book) {
		$modal.open({
			templateUrl : 'books/modal/book-modal.html',
			controller : 'BookModalController',
			size : 'lg',
			resolve : {
				book : function() {
					return book;
				}
			}
		}).result.then(function(response) {
			Flash.create('success', 'Book "' + response.title + '" updated successfully!', 'custom-class');
			$scope.search();
		}, function() {
			Flash.create('danger', 'Failed to update book!', 'custom-class');
		});
	};
});
