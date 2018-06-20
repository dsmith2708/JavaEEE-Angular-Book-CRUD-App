package com.qa.book.app.persistence;

public interface BookRepository {
	
	String getAllBooks();
	
	String createBook(String bookJson);
	
	String deleteBook(long bookID);
}
