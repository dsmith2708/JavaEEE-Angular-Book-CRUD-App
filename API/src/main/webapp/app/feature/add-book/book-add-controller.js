"use strict";

(function() {

    var BookAddController =  function(bookService, $log) {

    	var vm = this;
        vm.formData = {};

        vm.isHidden = false;

        vm.hideTable = function()
        {
        	vm.isHidden = !vm.isHidden
        };

        vm.showJsonFormResult = function() {
          console.log("In show form result");
          var jsonResult = {"title" :  vm.formData.newBookTitle,
                            "releaseYear" : vm.formData.newBookReleaseYear,
                            "genre" : vm.formData.newBookGenre,
                            "authors" : [
                              {
                                "authorTitle" : vm.formData.newBookAuthorTitle,
                                "authorFirstName" : vm.formData.newBookAuthorFirstName,
                                "authorLastName" : vm.formData.newBookAuthorLastName
                              }
                            ]
                          };
          console.log(JSON.stringify(jsonResult));
          console.log(bookService.addBook(jsonResult));
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

    angular.module('bookApp').controller('bookAddController', ['bookService','$log', BookAddController]);
}());
