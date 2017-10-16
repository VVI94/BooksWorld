package models.entities;

public class FavouriteBooks {
	private long favourite_book_id;
	private long users_user_id;
	private long users_id;
	
	public FavouriteBooks(long users_user_id, long users_id) {
		this.users_user_id = users_user_id;
		this.users_id = users_id;
	}


	public FavouriteBooks(long favourite_book_id, long users_user_id, long users_id) {
		this(users_user_id, users_id);
		this.favourite_book_id = favourite_book_id;
	}


	public long getFavourite_book_id() {
		return favourite_book_id;
	}


	public long getUsers_user_id() {
		return users_user_id;
	}


	public long getUsers_id() {
		return users_id;
	}
	
	
	
	

}
