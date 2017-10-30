package models.entities;

import java.util.Collections;
import java.util.List;

import exceptions.ValidationException;
import models.Validators;
import models.entities.comments.Comment;

public class Book {

	private long id;
	private String title;
	private Author author;
	private String description;
	private int year;
	private String publisher;
	private double price;
	private String photo;
	private String category;


	private List<Comment> comments;

	public Book(String title, String description, int year, String publisher, double price, String category, Author author, String photo) throws ValidationException {

		this.setTitle(title);
		this.setDescription(description);
		this.setYear(year);
		this.setPublisher(publisher);
		this.setPrice(price);
		this.setCategory(category);
		this.setAuthor(author);
		this.setPhoto(photo);

	}

	public Book(long id, String title, Author author, String description, int year, String publisher, double price,
			String category, List<Comment> comments, String photo) throws ValidationException {
		this(title, description, year, publisher, price, category, author, photo);
		this.id = id;
		this.setComments(comments);
	}
	
	
	public List<Comment> getComments() {
		return Collections.unmodifiableList(this.comments);
	}

	private void setComments(List<Comment> comments) throws ValidationException {
		Validators.vlidateInputForNull("comments", comments);

		this.comments = comments;
	}
	
	
	

	 public String getPhoto(){
	 return this.photo;
	 }
	
	 private void setPhoto(String photo) throws ValidationException {
	 Validators.vlidateInputForNull("photo", photo);
	
	 this.photo = photo;
	 }

	

	public long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	private void setTitle(String title) throws ValidationException {

		Validators.vlidateInputForNull("title", title);

		this.title = title;
	}

	public Author getAuthor() {
		return this.author;
	}

	private void setAuthor(Author author) throws ValidationException {

		Validators.vlidateInputForNull("author", author);

		this.author = author;
	}

	public String getDescription() {
		return this.description.trim();
	}

	private void setDescription(String description) throws ValidationException {

		Validators.vlidateInputForNull("description", description);

		this.description = description;
	}

	public int getYear() {
		return this.year;
	}

	private void setYear(int year) throws ValidationException {

		if (year < 0) {
			throw new ValidationException("Invalid year!");
		}
		this.year = year;
	}

	public String getPublisher() {
		return this.publisher;
	}

	private void setPublisher(String publisher) throws ValidationException {

		Validators.vlidateInputForNull("publisher", publisher);

		this.publisher = publisher;
	}

	public double getPrice() {

		return this.price;
	}

	private void setPrice(double price) throws ValidationException {

		if (this.price < 0) {
			throw new ValidationException("Invalid price!");
		}

		this.price = price;
	}

	public String getCategory() {
		return this.category;
	}
	
	
	public String firstName(){
		return this.author.getFirstName();
	}
	
	public String lastName(){
		return this.author.getLastName();
	}
	
	
	private void setCategory(String category) throws ValidationException {

		Validators.vlidateInputForNull("category", category);

		this.category = category;
	}

}
