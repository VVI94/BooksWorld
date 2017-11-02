package models.DBmodels;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.DBInterfaces.IOrderDAO;
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

			PreparedStatement ps = this.getCon().prepareStatement(
					"INSERT INTO orders(date, users_id)" + " VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setDate(1, order.getDate());
			ps.setLong(2, order.getUser_id());
			ps.executeUpdate();

			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getLong(1);
		}
	}

	@Override
	public void removeOrder(long orderId) throws SQLException {
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
	public long getOrderId(Date localDateTime, long userId) throws SQLException, UnexistingException {
		PreparedStatement ps = this.getCon()
				.prepareStatement("SELECT id FROM orders" + "	WHERE (date = ? AND users_id = ?)");

		ps.setDate(1, localDateTime);
		ps.setLong(2, userId);

		ResultSet result = ps.executeQuery();

		if (!result.next()) {
			throw new UnexistingException("Order with date " + localDateTime + " and userId " + userId + " doesn't exists");
		}

		return result.getLong("id");
	}

	@Override
	public Order getOrder(long orderId) throws SQLException, UnexistingException, ValidationException {
		PreparedStatement ps = this.getCon().prepareStatement("SELECT * FROM orders WHERE id = ?");
		ps.setLong(1, orderId);
		
		ResultSet result = ps.executeQuery();
		
		if(!result.next()){
			throw new UnexistingException("Order with id: " + orderId + " doesn't exist!");
		}
		
		return new Order(orderId, result.getDate("date"), result.getLong("users_id"));
		
	}

}
