package com.qa.book.app.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.book.app.persistence.BookRepository;

public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = Logger.getLogger(BookService.class);
	
	
	@Inject
	BookRepository repo;
	
	@Override
	public String getAllBooks() {
		LOGGER.info("In BookServiceImpl getAllAccounts ");
		return repo.getAllBooks();
	}

	@Override
	public String addBook(String bookJson) {
		LOGGER.info("In BookServiceImpl addBook ");
		return repo.createBook(bookJson);
	}

	@Override
	public String deleteBook(Long bookID) {
		LOGGER.info("In BookServiceImpl deleteBook ");
		return repo.deleteBook(bookID);
	}

	public void setRepo(BookRepository repo) {
		this.repo = repo;
	}

	@Override
	public String addExampleData() {
		return repo.addExampleData();
	}

	@Override
	public String updateBook(String bookJson) {
		LOGGER.info("In BookServiceImpl updateBook ");
		return repo.updateBook(bookJson);
	}

}
