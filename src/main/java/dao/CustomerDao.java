package dao;

import java.sql.SQLException;
import java.util.List;

import model.Customer;

public interface CustomerDao {
	 
		void addCustomer(Customer customer) throws SQLException, ClassNotFoundException;
	    void removeCustomer(int customerID) throws SQLException, ClassNotFoundException;
	    List<Customer> listCustomers() throws SQLException, ClassNotFoundException;
	    Customer findCustomerById(int customerID) throws SQLException, ClassNotFoundException;

}
