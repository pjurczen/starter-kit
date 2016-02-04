describe('author service', function () {
    'use strict';

    beforeEach(function () {
        module('app.authors');
    });
    
    var $authorService;
    var $authorRestService;
    
    beforeEach(inject(function(authorService, authorRestService) {
        $authorService = authorService;
        $authorRestService = authorRestService;
    }));
    
    it('search is defined', inject(function () {
        //then
        expect($authorService.search).toBeDefined();
    }));
    
    it('search should call authorRestService.search', inject(function () {
        //given
        spyOn($authorRestService, 'search');
        //when
        $authorService.search();
        //then
        expect($authorRestService.search).toHaveBeenCalled();
    }));
});
