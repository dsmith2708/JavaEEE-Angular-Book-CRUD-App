package com.qa.book.app.persistence;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.book.app.domain.Author;
import com.qa.book.app.domain.Book;
import com.qa.book.app.util.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.HashSet;

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
		Query query = manager.createQuery("Select a FROM Book a");
		Collection<Book> books = (Collection<Book>) query.getResultList();
		return jsonUtil.getJSONForObject(books);
	}

	@Transactional(REQUIRED)
	public String createBook(String bookJson) {
		LOGGER.info("BookDBRepository CreateBook");
		Book accountToAdd = jsonUtil.getObjectForJSON(bookJson, Book.class);
		manager.persist(accountToAdd);
		return "{\"message\": \"book has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
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
	
	@Transactional(REQUIRED)
	public String updateBook(String bookJson) {
		Book bookFromJson = jsonUtil.getObjectForJSON(bookJson, Book.class);
		Book bookFromDB = manager.find(Book.class, bookFromJson.getId());
		bookFromDB.setTitle(bookFromJson.getTitle());
		bookFromDB.setGenre(bookFromJson.getGenre());
		bookFromDB.setReleaseYear(bookFromJson.getReleaseYear());
		bookFromDB.setAuthors(bookFromJson.getAuthors());
		return "{\"message\": \"book successfully updated\"}";
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.jsonUtil = util;
	}

	@Transactional(REQUIRED)
	public String addExampleData() {
		Book newBook = new Book();
		newBook.setTitle("Wind");
		newBook.setGenre("Childrens");
		newBook.setReleaseYear("1996");
		HashSet<Author> authors = new HashSet<Author>();
		authors.add(new Author("Mr", "Daniel", "Smith"));
		newBook.setAuthors(authors);
		manager.persist(newBook);
		
		Book newBookTwo = new Book();
		newBookTwo.setTitle("SuperMan");
		newBookTwo.setGenre("Comic");
		newBookTwo.setReleaseYear("1960");
		HashSet<Author> authorsTwo = new HashSet<Author>();
		authorsTwo.add(new Author("Mr", "Jack", "Jones"));
		authorsTwo.add(new Author("Mr", "David", "Summer"));
		newBookTwo.setAuthors(authorsTwo);
		manager.persist(newBookTwo);
		
		Book newBookThree = new Book();
		newBookThree.setTitle("A Song of Ice and Fire");
		newBookThree.setGenre("Fantasy");
		newBookThree.setReleaseYear("2004");
		HashSet<Author> authorsThree = new HashSet<Author>();
		authorsThree.add(new Author("Sir", "Sam", "Hunnable"));
		newBookThree.setAuthors(authorsThree);
		manager.persist(newBookThree);
		
		return "{\"message\": \"data has been successfully added\"}";
	}

}
