package models.entities;

import java.sql.Date;

import exceptions.ValidationException;

public class Order {
	private long id;
	private Date date;
	private long user_id;

	public Order(Date date, long user_id) throws ValidationException {
		super();
		setDate(date);
		this.user_id = user_id;
	}

	public Order(long id, Date date, long user_id) throws ValidationException {
		this(date, user_id);
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) throws ValidationException {
		if(!date.after(getDate()))
		this.date = date;
		else{
			throw new ValidationException("Invalid date!");
		}
	}

	public long getId() {
		return id;
	}

	public long getUser_id() {
		return user_id;
	}
}
