package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import Exception.CustomerNotFoundException;
import util.DBConnUtil;

public class CustomerDaoImpl implements CustomerDao {

    Connection connection;

    

    @Override
    public void addCustomer(Customer customer) throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        String sql = "INSERT INTO Customer (customerID, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, customer.getCustomerID());
        pst.setString(2, customer.getFirstName());
        pst.setString(3, customer.getLastName());
        pst.setString(4, customer.getEmail());
        pst.setString(5, customer.getPhoneNumber());
        pst.executeUpdate();
    }

    @Override
    public void removeCustomer(int customerID) throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        String sql = "DELETE FROM Customer WHERE customerID = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, customerID);
        int rows = pst.executeUpdate();
        if (rows == 0) {
            throw new CustomerNotFoundException("Customer ID not found: " + customerID);
        }
    }

    @Override
    public List<Customer> listCustomers() throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        Statement pst = connection.createStatement();
        ResultSet rs = pst.executeQuery(sql);
        while (rs.next()) {
            customers.add(mapCustomer(rs));
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(int customerID) throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, customerID);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return mapCustomer(rs);
        } else {
            throw new CustomerNotFoundException("Customer ID not found: " + customerID);
        }
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
    	
        return new Customer(
            rs.getInt("customerID"),
            rs.getString("firstName"),
            rs.getString("lastName"),
            rs.getString("email"),
            rs.getString("phoneNumber")
        );
    }
}
