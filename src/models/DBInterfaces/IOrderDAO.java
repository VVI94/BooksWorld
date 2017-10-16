package models.DBInterfaces;

import java.sql.Date;
import java.sql.SQLException;

import exceptions.UnexistingException;
import exceptions.ValidationException;
import models.entities.Order;

public interface IOrderDAO {
	
	long addOrder(Order order) throws SQLException;

	void removeOrder(long orderId) throws SQLException;

	long getOrderId(Date date, long userId) throws SQLException, UnexistingException;

	Order getOrder(long orderId) throws SQLException, UnexistingException, ValidationException;

}
