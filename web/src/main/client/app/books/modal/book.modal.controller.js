angular.module('app.books').controller('BookModalController', function($scope, $modalInstance, bookService, book) {
			'use strict';
			
			$scope.book = angular.copy(book);
			$scope.firstName = '';
			$scope.lastName = '';

			$scope.addBook = function() {
				if ($scope.book.title !== '' && $scope.book.authors.length > 0) {
					bookService.addBook($scope.book).then(function(){
						$scope.$close(book);
					});
				} else {
					$scope.$dismiss();
				}
			};

			$scope.addAuthor = function(authorFirstName, authorLastName) {
				$scope.book.authors.push({
					id : null,
					firstName : authorFirstName,
					lastName : authorLastName
				});
				$scope.firstName = '';
				$scope.lastName = '';
			};

			$scope.deleteAuthor = function(authorIndex) {
				$scope.book.authors.splice(authorIndex, 1);
			};
		});