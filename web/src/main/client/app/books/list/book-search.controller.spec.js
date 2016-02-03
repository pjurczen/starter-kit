describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.books');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));
    
    it('search should call bookService.search', inject(function ($controller, $q, bookService, Flash) {
        //given
        var result = {
                'data' : [ {
                    'id' : 2, 'title' : 'Druga ksiazka', 'authors' : [ {
                        'id' : 8, 'firstName' : 'Zbigniew', 'lastName' : 'Nowak'
                    } ]
                } ]
            };
        var titlePrefix = 'druga';
        $controller('BookSearchController', {$scope: $scope});
        var searchDeferred = $q.defer();
        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
        //when
        $scope.prefix = titlePrefix;
        searchDeferred.resolve(result);
        $scope.search();
        $scope.$digest();
        //then
        expect(bookService.search).toHaveBeenCalledWith(titlePrefix);
    }));
    
    it('search book should fail call bookService', inject(function($controller, $q, bookService, Flash) {
        // given
        $controller('BookSearchController', {$scope: $scope});
        var searchDeferred = $q.defer();
        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.search();
        searchDeferred.reject();
        $scope.$digest();
        // then
        expect(bookService.search).toHaveBeenCalled();
        expect(Flash.create).toHaveBeenCalledWith('danger', 'Failed to load books!', 'custom-class');
    }));
    
    it('add book should open modal', inject(function($controller, $modal, $q, Flash) {
        // given
        $controller('BookSearchController', {$scope: $scope});
        var testBook = {
            id : 1, title : 'random book'
        };
        var modalDeferred = $q.defer();
        spyOn($modal, 'open').and.returnValue({
            result : modalDeferred.promise
        });
        spyOn(Flash, 'create');
        // when
        $scope.addBook();
        modalDeferred.resolve(testBook);
        $scope.$digest();
        // then
        expect($modal.open).toHaveBeenCalled();
        expect(Flash.create).toHaveBeenCalledWith('success', 'Book "' + testBook.title + '" added successfully!',
        'custom-class');
    }));
    
    it('delete book should call bookService.deleteBook', inject(function ($controller, $q, bookService, Flash) {
        // given
        $controller('BookSearchController', {$scope: $scope});
        var bookId = 1;
        $scope.books = [{id: bookId, title: 'test'}];
        var deleteDeferred = $q.defer();
        spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.deleteBook(bookId);
        deleteDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Book successfully deleted.', 'custom-class');
        expect($scope.books.length).toBe(0);
    }));
});
