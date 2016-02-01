angular.module('app.books').controller(
		'BookModalController',
		function($scope, $modalInstance,
				bookService, book, header) {
			'use strict';
			
			$scope.header = header;
			$scope.book = book;
			$scope.firstName = '';
			$scope.lastName = '';
			$scope.gridOptions = {
				data : 'book'
			};
			$scope.authors = [];

			$scope.addBook = function() {
				if ($scope.book.title !== '' && $scope.authors.length > 0) {
					for (var i = 0; i < $scope.authors.length; i++) { 
						$scope.book.authors += $scope.authors[i].firstName + ' ' + $scope.authors[i].lastName;
						if(($scope.authors.length - i) > 1) {
							$scope.book.authors += ', ';
						}
					}
					bookService.addBook($scope.book).then(function(){
						$scope.$close(book);
					});
				} else {
					$scope.$dismiss();
				}
			};

			$scope.addAuthor = function(authorFirstName, authorLastName) {
				$scope.authors.push({
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