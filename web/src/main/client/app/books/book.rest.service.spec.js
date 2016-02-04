describe('book rest service', function() {
    'use strict';

    beforeEach(function() {
        module('app.books');
    });

    var httpBackend;
    var $bookRestService;
    
    beforeEach(inject(function($httpBackend, bookRestService) {
        httpBackend = $httpBackend;
        $bookRestService = bookRestService;
    }));

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });
    
    it('search is defined', inject(function () {
        //then
        expect($bookRestService.search).toBeDefined();
    }));
    
    it('deleteBook is defined', inject(function () {
        //then
        expect($bookRestService.deleteBook).toBeDefined();
    }));
    
    it('addBook is defined', inject(function () {
        //then
        expect($bookRestService.addBook).toBeDefined();
    }));

    it('search rest service should return books', inject(function() {
        // given
        var testPrefix = 'druga';
        var url = '/context.html/services/books/books-by-title?titlePrefix=' + testPrefix;
        var testBooks = [ {
                'id' : 2, 'title' : 'Druga ksiazka', 'authors' : [ {
                    'id' : 8, 'firstName' : 'Zbigniew', 'lastName' : 'Nowak'
                    } ]
            } ];
        // when
        $bookRestService.search(testPrefix);
        // then
        httpBackend.expectGET(url).respond(200, testBooks);
        httpBackend.flush();
    }));

    it('deleteBook rest service should delete book', inject(function() {
        // given
        var testBookId = 1;
        var url = '/context.html/services/books/book/' + testBookId;
        var testBooks = [ {
                'id' : 2, 'title' : 'Druga ksiazka', 'authors' : [ {
                    'id' : 8, 'firstName' : 'Zbigniew', 'lastName' : 'Nowak'
                    } ]
            } ];
        // when
        $bookRestService.deleteBook(testBookId);
        // then
        httpBackend.expectDELETE(url).respond(200);
        httpBackend.flush();
    }));
    
    it('addBook rest service should add book', inject(function() {
        // given
        var testBook = {
                'id' : 2, 'title' : 'Druga ksiazka', 'authors' : [ {
                    'id' : 8, 'firstName' : 'Zbigniew', 'lastName' : 'Nowak'
                    } ]
            };
        var url = '/context.html/services/books/book/';
        var testBooks = [ {
                'id' : 2, 'title' : 'Druga ksiazka', 'authors' : [ {
                    'id' : 8, 'firstName' : 'Zbigniew', 'lastName' : 'Nowak'
                    } ]
            } ];
        // when
        $bookRestService.addBook(testBook);
        // then
        httpBackend.expectPOST(url).respond(200, testBooks);
        httpBackend.flush();
    }));
});