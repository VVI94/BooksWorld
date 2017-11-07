package models.entities;

public class Stock {

	private Book book;
	private double quantity;
	public Stock(Book book, double quantity) {
		
		this.book = book;
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public double getQuantity() {
		return quantity;
	}
	
	
	
}
