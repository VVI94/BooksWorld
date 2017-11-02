package models.DBmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IOrderDAO;
import models.entities.Book;
import models.entities.Order;

public class OrderDAO extends DAO implements IOrderDAO {

	private static IOrderDAO instance;

	private OrderDAO() {
	}

	public static synchronized IOrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAO();
		}

		return instance;
	}

	@Override
	public long addOrder(Order order) throws SQLException {
		try {
			return this.getOrderId(order.getDate(), order.getUser_id());
		} catch (UnexistingException e) {
			Map<Book, Integer> products = new HashMap<Book, Integer>();

			PreparedStatement ps = this.getCon().prepareStatement(
					"INSERT INTO orders(date, users_id)" + " VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setTimestamp(1, order.getDate());
			ps.setLong(2, order.getUser_id());
			ps.executeUpdate();

			ResultSet id = ps.getGeneratedKeys();
			id.next();
			long orderId = id.getLong(1);
			for (Book book : products.keySet()) {
				PreparedStatement ps1 = this.getCon().prepareStatement(
						"INSERT INTO books_has_order (books_book_id, orders_orders_id, count)" + "VALUES (?, ?, ?)");

				ps1.setLong(1, book.getId());
				ps1.setLong(1, orderId);
				ps.executeUpdate();
			}

			return orderId;
		}
	}

	@Override
	public void removeOrder(long orderId) throws SQLException {

		// boolean isInTransaction = false;
		//
		// if (!this.getCon().getAutoCommit()) {
		// isInTransaction = true;
		// }
		//
		// try{
		// this.getCon().setAutoCommit(false);
		//
		// for (Long bookId :
		// BookDAO.getInstance().getBookIDsByAuthor(authorId)) {
		// BookDAO.getInstance().removeBook(bookId);
		// }
		//
		// PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM
		// authors WHERE author_id = ?");
		// ps1.setLong(1, authorId);
		// ps1.executeUpdate();
		// }catch (SQLException e) {
		// this.getCon().rollback();
		// throw new SQLException("Can't remove this author",e);
		// }finally {
		// if(!isInTransaction){
		// this.getCon().commit();
		// this.getCon().setAutoCommit(true);
		// }
		// }

		////////////////////////////////////////
		this.getCon().setAutoCommit(false);
		PreparedStatement ps = this.getCon().prepareStatement("DELETE FROM orders_has_books WHERE orders_id = ?");
		ps.setLong(1, orderId);
		ps.executeUpdate();

		PreparedStatement ps1 = this.getCon().prepareStatement("DELETE FROM orders WHERE id = ?");
		ps1.setLong(1, orderId);
		ps1.executeUpdate();

		this.getCon().commit();

		this.getCon().setAutoCommit(true);

	}

	@Override
	public long getOrderId(Timestamp localDateTime, long userId) throws SQLException, UnexistingException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT id FROM orders" + "	WHERE (date = ? AND users_id = ?)");

		ps.setTimestamp(1, localDateTime);
		ps.setLong(2, userId);

		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException(
					"Order with date " + localDateTime + " and userId " + userId + " doesn't exists");
		}

		return result.getLong("id");
	}

	@Override
	public Order getOrder(long orderId) throws SQLException, UnexistingException, ValidationException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM orders WHERE id = ?");
		ps.setLong(1, orderId);

		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException("Order with id: " + orderId + " doesn't exist!");
		}

		return new Order(orderId, result.getTimestamp("date"), result.getLong("users_id"));

	}

	@Override
	public Set<Order> getAllOrders(long user_id) throws SQLException, ValidationException, UnexistingException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM orders WHERE users_user_id = ?");
		ps.setLong(1, user_id);

		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException("No orders!");
		}
		Set<Order> allOrders = new HashSet<Order>();

		while (result.next()) {
			long orderId = result.getLong("orders_id");
			Timestamp date = result.getTimestamp("date");
			long userId = result.getLong("users_user_id");

			allOrders.add(new Order(orderId, date, userId, this.getAllProducts(orderId)));
		}

		return allOrders;
	}

	@Override
	public Map<Book, Integer> getAllProducts(long orderId) throws SQLException, UnexistingException, ValidationException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT * FROM books_has_orders WHERE orders_orders_id = ? ");
		ps.setLong(1, orderId);
		ResultSet result = ps.executeQuery();

		while (result.next()) {
			long bookId = result.getLong("books_book_id");
			BookDAO.getInstance().getBook(bookId);

		}

	}

}
