package com.qa.book.app.interoperability;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.book.app.business.BookService;



@Path("/book")
public class BookEndPoint {
	private static final Logger LOGGER = Logger.getLogger(BookEndPoint.class);
	
	@Inject
	BookService service;
	
	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		LOGGER.info("BookEndPoint + getAllAccounts");
		return service.getAllAccounts();
	}
	
	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String createBook(String bookJson) {
		LOGGER.info("BookEndPoint + createBook");
		return service.addBook(bookJson);
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteBook(@PathParam("id") Long id) {
		LOGGER.info("AccountEndpoint + deleteAccount");
		return service.deleteBook(id);

	}
	
	@Path("/addExampleData")
	@GET
	@Produces({ "application/json" })
	public String addExampleData() {
		
		return service.addExampleData();
	}
	
	public void setService(BookService service) {
		LOGGER.info("BookEndPoint + setService");
		this.service = service;
	}
}
