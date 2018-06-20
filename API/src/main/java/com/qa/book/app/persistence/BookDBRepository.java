package com.qa.book.app.persistence;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.book.app.domain.Book;
import com.qa.book.app.util.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

@Default
@Transactional(SUPPORTS)
public class BookDBRepository implements BookRepository {
	
	private static final Logger LOGGER = Logger.getLogger(BookDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	JSONUtil jsonUtil;
	
	public String getAllBooks() {
		LOGGER.info("BookDBRepository getAllAccounts");
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Book> books = (Collection<Book>) query.getResultList();
		return jsonUtil.getJSONForObject(books);
	}

	
	public String createBook(String bookJson) {
		LOGGER.info("BookDBRepository CreateBook");
		Book accountToAdd = jsonUtil.getObjectForJSON(bookJson, Book.class);
		manager.persist(accountToAdd);
		return "{\"message\": \"book has been sucessfully added\"}";
	}

	
	public String deleteBook(long bookID) {
		LOGGER.info("BookDBRepository CreateBook");
		Book bookFromDB = manager.find(Book.class, bookID);
		if (bookFromDB != null) {
			manager.remove(bookFromDB);
			return "{\"message\": \"book has been sucessfully removed\"}";
		}
		else {
			return "{\"message\": \"book not found in database\"}";
		}
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.jsonUtil = util;
	}

}
