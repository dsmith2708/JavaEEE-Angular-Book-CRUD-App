package com.qa.book.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Author {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Size(min = 2, max = 15)
	private String authorTitle;
	@Size(min = 2, max = 80)
	private String authorFirstName;
	@Size(min = 2, max = 80)
	private String authorLastName;
	
	public Author() {
		
	}
	
	public Author (String authorTitle, String authorFirstName, String authorLastName) {
		this.authorTitle = authorTitle;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
	}

	public String getAuthorTitle() {
		return authorTitle;
	}

	public void setAuthorTitle(String authorTitle) {
		this.authorTitle = authorTitle;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public Long getId() {
		return id;
	}
	
	
}
