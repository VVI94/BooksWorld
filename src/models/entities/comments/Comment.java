package models.entities.comments;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import exceptions.ValidationException;
import models.Validators;
import models.entities.User;


public class Comment extends Reply{

	private List<Reply> replyComments;

	public Comment(String content, Timestamp date) throws ValidationException {

		super(content, date);
	}
	
	public Comment(long id, String content, Timestamp date, User user, List<Reply> replyComments, int likes, int dislikes) throws ValidationException {
		super(id, content, date, user, likes, dislikes);
		this.setReplyComments(replyComments);
	}
	
	public List<Reply> getReplyComments(){
		return Collections.unmodifiableList(replyComments);
	}

	private void setReplyComments(List<Reply> replyComments) throws ValidationException {
		Validators.vlidateInputForNull("comments", replyComments);
		
		this.replyComments = replyComments;
		
	}


}
