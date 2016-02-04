describe('author rest service', function() {
    'use strict';

    beforeEach(function() {
        module('app.authors');
    });

    var httpBackend;
    var $authorRestService;
    
    beforeEach(inject(function($httpBackend, authorRestService) {
        httpBackend = $httpBackend;
        $authorRestService = authorRestService;
    }));

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });
    
    it('search is defined', inject(function () {
        //then
        expect($authorRestService.search).toBeDefined();
    }));
    
    it('search rest service should return books', inject(function() {
        // given
        var url = '/context.html/services/authors';
        var testAuthors = [ {
                    'id' : 1, 'firstName' : 'Zbigniew', 'lastName' : 'Stonoga'
            } ];
        // when
        $authorRestService.search();
        // then
        httpBackend.expectGET(url).respond(200, testAuthors);
        httpBackend.flush();
    }));
});