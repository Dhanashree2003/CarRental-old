package dao;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	 
		void addCustomer(Customer customer);
	    void removeCustomer(int customerID);
	    List<Customer> listCustomers();
	    Customer findCustomerById(int customerID);

}
