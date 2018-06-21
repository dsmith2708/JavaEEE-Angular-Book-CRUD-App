"use strict";

(function() {

    var BookController =  function(bookService, $log) {

    	var vm = this;

        vm.bookToUpdate = {};
        vm.showUpdate = false;

        vm.isHidden = false;

        vm.hideTable = function()
        {
        	vm.isHidden = !vm.isHidden
        };
        vm.updateClick = function()
        {
        	vm.isHidden = !vm.isHidden
        };

        vm.deleteBook = function(bookToDelete) {
          bookService.deleteBook(bookToDelete);
          location.reload();
        }

        vm.updateBook = function(selectedBook) {
          vm.showUpdate = true;
          vm.bookToUpdate.id = selectedBook.id;
          vm.bookToUpdate.title = selectedBook.title;
          vm.bookToUpdate.releaseYear = selectedBook.releaseYear;
          vm.bookToUpdate.genre = selectedBook.genre;
          vm.bookToUpdate.authorID = selectedBook.authors[0].id;
          vm.bookToUpdate.authorTitle = selectedBook.authors[0].authorTitle;
          vm.bookToUpdate.authorFirstName = selectedBook.authors[0].authorFirstName;
          vm.bookToUpdate.authorLastName = selectedBook.authors[0].authorLastName;
        }

        vm.saveUpdate = function() {
          vm.showUpdate = false;
          var jsonResult = {"id" : vm.bookToUpdate.id,
                            "title" :  vm.bookToUpdate.title,
                            "releaseYear" : vm.bookToUpdate.releaseYear,
                            "genre" : vm.bookToUpdate.genre,
                            "authors" : [
                              {
                                "id" : vm.bookToUpdate.authorID,
                                "authorTitle" : vm.bookToUpdate.authorTitle,
                                "authorFirstName" : vm.bookToUpdate.authorFirstName,
                                "authorLastName" : vm.bookToUpdate.authorLastName
                              }
                            ]
                          };
          console.log(jsonResult);
        };

        function init() {
        	bookService.getBooks().then(function (results) {
           	vm.books = results;
           	$log.log("In the book controller the value of the result promise is ");
        	$log.log(JSON.stringify(vm.books));
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
       }

       init();

    };

    angular.module('bookApp').controller('bookController', ['bookService','$log', BookController]);
}());
