"use strict";

(function() {

    var BookAddController =  function(bookService, $log) {

    	var vm = this;

        vm.isHidden = false;

        vm.hideTable = function()
        {
        	vm.isHidden = !vm.isHidden
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
