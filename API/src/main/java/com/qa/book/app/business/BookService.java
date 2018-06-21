package com.qa.book.app.business;

public interface BookService {

	String getAllBooks();
	
	String updateBook(String bookJson);

	String addBook(String bookJson);

	String deleteBook(Long bookID);
	
	String addExampleData();

}
