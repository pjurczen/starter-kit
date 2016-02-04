describe('book modal controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.books');
    });

    var $scope;
    var modalInstance;
    var controller;
    
    beforeEach(inject(function ($rootScope, $controller, bookService) {
        $scope = $rootScope.$new();
        modalInstance = {
            close : jasmine.createSpy('modalInstance.close'), dismiss : jasmine.createSpy('modalInstance.dismiss'),
            result : {
                then : jasmine.createSpy('modalInstance.result.then')
            }
        };
        var testBook = {
                id : 1, title : 'random book', 'authors': [{
                    'id' : 1, 'firstName' : 'Zenek', 'lastName' : 'Stonoga'
                }]
        };
        controller = $controller('BookModalController', {
            $scope : $scope, $modalInstance : modalInstance, bookService : bookService, book : testBook
        });
    }));

    it('addBook should call bookService addBook', inject(function($q, bookService) {
        // given
        $scope.$close = jasmine.createSpy('$close');
        var addDeferred = $q.defer();
        spyOn(bookService, 'addBook').and.returnValue(addDeferred.promise);
        // when
        $scope.addBook();
        $scope.$digest();
        // then
        expect(bookService.addBook).toHaveBeenCalledWith($scope.book);
    }));
    
    it('addAuthor should add author', inject(function() {
        // given
        var testAuthorFirstName = 'Wiesiek';
        var testAuthorLastName = 'Graczyk';
        spyOn($scope.book.authors, 'push').and.callThrough();
        // when
        $scope.addAuthor(testAuthorFirstName, testAuthorLastName);
        // then
        expect($scope.book.authors.push).toHaveBeenCalledWith({
            id : null,
            firstName : testAuthorFirstName,
            lastName : testAuthorLastName
        });
        expect($scope.book.authors.length).toEqual(2);
        expect($scope.book.authors[1].firstName).toEqual(testAuthorFirstName);
        expect($scope.book.authors[1].lastName).toEqual(testAuthorLastName);
        expect($scope.firstName).toEqual('');
        expect($scope.lastName).toEqual('');
    }));
    
    it('deleteAuthor should delete author', inject(function() {
        // given
        var lastAuthorIndex = $scope.book.authors.length - 1;
        spyOn($scope.book.authors, 'splice').and.callThrough();
        // when
        $scope.deleteAuthor(lastAuthorIndex);
        // then
        expect($scope.book.authors.length).toEqual(lastAuthorIndex);
    }));
    
});
