package models.entities;

import java.sql.Date;

import exceptions.ValidationException;
import models.Validators;

public class Comment {

	private long id;

	private String content;

	private Date date;

	public Comment(String content, Date date) {

		this.content = content;
		this.date = date;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) throws ValidationException {

		Validators.vlidateInputForNull("content", content);

		if (content.trim().isEmpty()) {
			throw new ValidationException("Content can't by empty!");
		}

		this.content = content;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) throws ValidationException {

		Validators.vlidateInputForNull("date", date);

		this.date = date;
	}

	public long getId() {
		return this.id;
	}

}
