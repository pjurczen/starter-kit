describe('book search controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.books');
    });

    var $scope;
    var controller;
    var $bookService;
    var $Flash;
    
    beforeEach(inject(function ($controller, $rootScope, bookService, Flash) {
        $Flash = Flash;
        $bookService = bookService;
        $scope = $rootScope.$new();
        controller = $controller('BookSearchController', {$scope: $scope});
    }));

    it('search is defined', inject(function () {
        //then
        expect($scope.search).toBeDefined();
    }));
    
    it('updateBook is defined', inject(function () {
        //then
        expect($scope.updateBook).toBeDefined();
    }));
    
    it('addBook is defined', inject(function () {
        //then
        expect($scope.addBook).toBeDefined();
    }));
    
    it('deleteBook is defined', inject(function () {
        //then
        expect($scope.deleteBook).toBeDefined();
    }));
    
    it('search should call bookService.search', inject(function ($q) {
        //given
        var result = {
                'data' : [ {
                    'id' : 2, 'title' : 'Druga ksiazka', 'authors' : [ {
                        'id' : 8, 'firstName' : 'Zbigniew', 'lastName' : 'Nowak'
                    } ]
                } ]
            };
        var titlePrefix = 'druga';
        var searchDeferred = $q.defer();
        spyOn($bookService, 'search').and.returnValue(searchDeferred.promise);
        //when
        $scope.prefix = titlePrefix;
        searchDeferred.resolve(result);
        $scope.search();
        $scope.$digest();
        //then
        expect($bookService.search).toHaveBeenCalledWith(titlePrefix);
    }));
    
    it('search book should fail call bookService.search', inject(function($q) {
        //given
        var searchDeferred = $q.defer();
        spyOn($bookService, 'search').and.returnValue(searchDeferred.promise);
        spyOn($Flash, 'create');
        //when
        $scope.search();
        searchDeferred.reject();
        $scope.$digest();
        //then
        expect($bookService.search).toHaveBeenCalled();
        expect($Flash.create).toHaveBeenCalledWith('danger', 'Failed to load books!', 'custom-class');
    }));
    
    it('add book should open modal', inject(function($modal, $q) {
        //given
        var modalDeferred = $q.defer();
        spyOn($modal, 'open').and.returnValue({
            result : modalDeferred.promise
        });
        //when
        $scope.addBook();
        //then
        expect($modal.open).toHaveBeenCalled();
    }));
    
    it('successfull adding book should flash success alert', inject(function($modal, $q) {
        //given
        var testBook = {
            id : 1, title : 'random book'
        };
        var addDeferred = $q.defer();
        spyOn($modal, 'open').and.returnValue({result:addDeferred.promise});
        spyOn($Flash, 'create');
        //when
        $scope.addBook();
        addDeferred.resolve(testBook);
        $scope.$digest();
        //then
        expect($Flash.create).toHaveBeenCalledWith('success', 'Book "' + testBook.title + '" added successfully!',
        'custom-class');
    }));
    
    it('failed adding book should flash danger alert', inject(function($modal, $q) {
        //given
        var addDeferred = $q.defer();
        spyOn($modal, 'open').and.returnValue({result:addDeferred.promise});
        spyOn($Flash, 'create');
        //when
        $scope.addBook();
        addDeferred.reject();
        $scope.$digest();
        //then
        expect($Flash.create).toHaveBeenCalledWith('danger', 'Failed to add book!', 'custom-class');
    }));
    
    
    it('delete book should call bookService.deleteBook', inject(function ($q) {
        //given
        var bookId = 1;
        $scope.books = [{id: bookId, title: 'test'}];
        var deleteDeferred = $q.defer();
        spyOn($bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
        spyOn($Flash, 'create');
        //when
        $scope.deleteBook(bookId);
        deleteDeferred.resolve();
        $scope.$digest();
        //then
        expect($bookService.deleteBook).toHaveBeenCalledWith(bookId);
        expect($Flash.create).toHaveBeenCalledWith('success', 'Book successfully deleted.', 'custom-class');
        expect($scope.books.length).toBe(0);
    }));
    
    it('update book should open modal', inject(function($modal, $q) {
        //given
        var modalDeferred = $q.defer();
        spyOn($modal, 'open').and.returnValue({
            result : modalDeferred.promise
        });
        //when
        $scope.updateBook();
        //then
        expect($modal.open).toHaveBeenCalled();
    }));
});
