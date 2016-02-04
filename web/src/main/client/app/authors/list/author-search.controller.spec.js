describe('author search controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.authors');
    });

    var $scope;
    var controller;
    var $authorService;
    var $Flash;
    
    beforeEach(inject(function ($rootScope, $controller, authorService, Flash) {
        $Flash = Flash;
        $scope = $rootScope.$new();
        $authorService = authorService;
        controller = $controller('AuthorSearchController', {$scope: $scope});
    }));

    it('search is defined', inject(function () {
        //then
        expect($scope.search).toBeDefined();
    }));
    
    it('search should call authorService.search', inject(function ($q) {
        //given
        var result = [ {
                    'id' : 1, 'firstName' : 'Wiesiek', 'lastName' : 'Graczyk'
            } ];
        var searchDeferred = $q.defer();
        spyOn($authorService, 'search').and.returnValue(searchDeferred.promise);
        //when
        $scope.search();
        searchDeferred.resolve(result);
        $scope.$digest();
        //then
        expect($authorService.search).toHaveBeenCalled();
    }));
    
    it('search book should fail call authorService.search', inject(function($q) {
        //given
        var searchDeferred = $q.defer();
        spyOn($authorService, 'search').and.returnValue(searchDeferred.promise);
        spyOn($Flash, 'create');
        //when
        $scope.search();
        searchDeferred.reject();
        $scope.$digest();
        //then
        expect($authorService.search).toHaveBeenCalled();
        expect($Flash.create).toHaveBeenCalledWith('danger', 'Failed to load authors!', 'custom-class');
    }));
});
