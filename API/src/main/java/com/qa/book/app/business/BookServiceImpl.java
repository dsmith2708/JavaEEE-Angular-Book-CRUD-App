package com.qa.book.app.business;

import javax.inject.Inject;

import com.qa.book.app.persistence.BookRepository;

public class BookServiceImpl implements BookService {

	@Inject
	BookRepository repo;
	
	@Override
	public String getAllAccounts() {
		return repo.getAllBooks();
	}

	@Override
	public String addBook(String bookJson) {
		return repo.createBook(bookJson);
	}

	@Override
	public String deleteBook(Long bookID) {
		return repo.deleteBook(bookID);
	}

	public void setRepo(BookRepository repo) {
		this.repo = repo;
	}

}
