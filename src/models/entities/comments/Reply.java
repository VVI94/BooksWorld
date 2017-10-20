package models.entities.comments;

import java.sql.Timestamp;
import exceptions.ValidationException;
import models.Validators;
import models.entities.User;

public class Reply {

	private long id;

	private String content;

	private Timestamp date;
	
	private User user;
	
	
	public Reply(String content, Timestamp date) throws ValidationException {

		this.setContent(content);
		this.setDate(date);
	}
	
	public Reply(long id, String content, Timestamp date, User user) throws ValidationException {
		this(content, date);
		this.id = id;
		this.user = user;
	}

	public String getContent() {
		return this.content;
	}

	private void setContent(String content) throws ValidationException {

		Validators.vlidateInputForNull("content", content);

		if (content.trim().isEmpty()) {
			throw new ValidationException("Content can't by empty!");
		}

		this.content = content;
	}

	public Timestamp getDate() {
		return this.date;
	}

	private void setDate(Timestamp date) throws ValidationException {

		Validators.vlidateInputForNull("date", date);

		this.date = date;
	}

	public long getId() {
		return this.id;
	}

	public User getUser() {
		return user;
	}
	
	
}
