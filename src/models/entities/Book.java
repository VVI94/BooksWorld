package models.entities;

import exceptions.ValidationException;
import models.Validators;

public class Book {
	
	private int id;
	private String title;
	private Author author;
	private String description;
	private int year;
	private String publisher;
	private double price;
	private String category;
	
	public Book(String title, Author author, String description, int year, 
				String publisher, double price, String category) throws ValidationException {
				
		this.setTitle(title);
		this.setAuthor(author);
		this.setDescription(description);
		this.setYear(year);
		this.setPublisher(publisher);
		this.setPrice(price);
		this.setCategory(category);
	}

	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) throws ValidationException {
		
		Validators.vlidateInputForNull("title", title);
		
		this.title = title;
	}

	public Author getAuthor() {		
		return this.author;
	}

	public void setAuthor(Author author) throws ValidationException {
		
		Validators.vlidateInputForNull("author", author);
		
		this.author = author;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) throws ValidationException {
		
		Validators.vlidateInputForNull("description", description);
		
		this.description = description;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) throws ValidationException {
		
		if(year < 0){
			throw new ValidationException("Invalid year!");
		}
		this.year = year;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) throws ValidationException {
		
		Validators.vlidateInputForNull("publisher", publisher);
		
		this.publisher = publisher;
	}

	public double getPrice(){
			
		return this.price;
	}

	public void setPrice(double price) throws ValidationException {
		
		if(this.price < 0){
			throw new ValidationException("Invalid price!");
		}
		
		this.price = price;
	}
	
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) throws ValidationException {
		
		Validators.vlidateInputForNull("category", category);
		
		this.category = category;
	}


	
	

}
