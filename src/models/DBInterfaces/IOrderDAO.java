package models.DBInterfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.Book;
import models.entities.Order;

public interface IOrderDAO {
	
	long addOrder(Order order) throws SQLException;

	void removeOrder(long orderId) throws SQLException;

	long getOrderId(Timestamp date, long userId) throws SQLException, UnexistingException;

	Order getOrder(long orderId) throws SQLException, UnexistingException, ValidationException;

	Set<Order> getAllOrders(long user_id)throws SQLException, ValidationException, UnexistingException ;
	
	Map<Book, Integer> getAllProducts(long orderId) throws SQLException, UnexistingException, ValidationException ;

}
