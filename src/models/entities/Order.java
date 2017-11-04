package models.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import exceptions.ValidationException;

public class Order {
	private long id;
	private Timestamp date;
	private long user_id;
	private Map<Book, Integer> products;

	

	public Map<Book, Integer> getProducts() {
		return Collections.unmodifiableMap(products);
	}

	public Order(Timestamp date, long user_id, Map<Book, Integer> products) throws ValidationException {
		this.date = date;
		this.user_id = user_id;
		this.products = new HashMap<Book, Integer>();
	}

	public Order(long id, Timestamp date, long user_id, Map<Book, Integer> products) throws ValidationException {
		this(date, user_id, products);
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public long getId() {
		return id;
	}

	public long getUser_id() {
		return user_id;
	}
}
