describe('author search controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.authors');
    });

    var $scope;
    var controller;
    var $authorService;
    var $Flash;
    
    beforeEach(inject(function ($httpBackend, $rootScope, $controller, authorService, Flash) {
        $httpBackend.whenGET('/context.html/services/authors').respond('');
        $Flash = Flash;
        $scope = $rootScope.$new();
        $authorService = authorService;
        controller = $controller('AuthorSearchController', {$scope: $scope});
    }));

    it('should call authorService.search after page is loaded', inject(function ($q) {
        //given
        var result = [ {
                    'id' : 1, 'firstName' : 'Wiesiek', 'lastName' : 'Graczyk'
            } ];
        var searchDeferred = $q.defer();
        spyOn($authorService, 'search').and.returnValue(searchDeferred.promise);
        //when
        $scope.init();
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
        $scope.init();
        searchDeferred.reject();
        $scope.$digest();
        //then
        expect($authorService.search).toHaveBeenCalled();
        expect($Flash.create).toHaveBeenCalledWith('danger', 'Failed to load authors!', 'custom-class');
    }));
});
