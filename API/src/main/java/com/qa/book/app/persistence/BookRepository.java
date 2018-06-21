package com.qa.book.app.persistence;

public interface BookRepository {
	
	String getAllBooks();
	
	String updateBook(String bookJson);
	
	String createBook(String bookJson);
	
	String deleteBook(long bookID);

	String addExampleData();
}
