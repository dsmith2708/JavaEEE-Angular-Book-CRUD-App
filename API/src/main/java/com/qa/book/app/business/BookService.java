package com.qa.book.app.business;

public interface BookService {

	String getAllAccounts();

	String addBook(String bookJson);

	String deleteBook(Long bookID);

}
