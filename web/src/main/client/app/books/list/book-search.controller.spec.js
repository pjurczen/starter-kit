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
        var titlePrefix = 'druga';
        $controller('BookSearchController', {$scope: $scope});
        var searchDeferred = $q.defer();
        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
        //when
        $scope.search();
        $scope.$digest();
        //then
        expect(bookService.search).toHaveBeenCalled();
    }));
    
    it('should find book by prefix', inject(function ($controller, $q, bookService) {
    	//given
    	var titlePrefix = 'p';
    	$controller('BookSearchController', {$scope: $scope});
    	var searchDeferred = $q.defer();
    	spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
    	//when
    	$scope.prefix = titlePrefix;
    	$scope.search();
    	searchDeferred.resolve(result);
    	$scope.$digest();
    	//then
    	expect(bookService.search).toHaveBeenCalled();
    	expect($scope.books.length).toBe(2);
    	expect($scope.books[0].id).toEqual(1);
    	expect($scope.books[1].id).toEqual(5);
    	expect($scope.books[0].title).toEqual('Pierwsza książka');
    	expect($scope.books[1].title).toEqual('Piata książka');
    	expect($scope.books[0].authors).not.toBeNull();
    	expect($scope.books[1].authors).not.toBeNull();
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
