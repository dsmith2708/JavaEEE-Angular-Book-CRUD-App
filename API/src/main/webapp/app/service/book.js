"use strict";

(function () {


    function BookService (bookDal) {
        this.addBook = function(bookJson)
        {
          return bookDal.saveBook(bookJson);
        };

        this.getBooks = function()
        {
        	return bookDal.getBooks();
        };

        this.deleteBook = function(bookToDelete) {
          return bookDal.deleteBook(bookToDelete);
        }

        this.updateBook = function(bookToUpdate) {
          console.log("in bookservice updatebook");
          return bookDal.updateBook(bookToUpdate);
        }
    }

    angular.module("bookApp").service("bookService", ['bookDal', BookService]);

}());
