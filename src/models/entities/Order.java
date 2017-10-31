package models.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;

import exceptions.ValidationException;

public class Order {
	private long id;
	private LocalDateTime date;
	private long user_id;
	private HashMap<Book, Integer> shoppingCart;

	public Order(LocalDateTime date, long user_id) throws ValidationException {
		super();
		this.date = date;
		this.user_id = user_id;
	}

	public Order(long id, LocalDateTime date, long user_id) throws ValidationException {
		this(date, user_id);
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public long getId() {
		return id;
	}

	public long getUser_id() {
		return user_id;
	}
}
