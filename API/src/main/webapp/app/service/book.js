"use strict";

(function () {


    function BookService (bookDal) {

        this.getBooks = function()
        {
        	return bookDal.getBooks();
        };

    }

    angular.module("bookApp").service("bookService", ['bookDal', BookService]);

}());
