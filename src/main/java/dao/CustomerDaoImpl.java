package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exception.CustomerNotFoundException;
import model.Customer;
import util.DBConnUtil;

public class CustomerDaoImpl implements CustomerDao{
	
	private Connection connection;     
    public CustomerDaoImpl() {
   	    try {
   	        connection = DBConnUtil.getConnection();
   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
   	}
    

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (customerID, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer.getCustomerID());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCustomer(int customerID) {
        String sql = "DELETE FROM Customer WHERE customerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new CustomerNotFoundException("Customer ID not found: " + customerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(int customerID) {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapCustomer(rs);
            } else {
                throw new CustomerNotFoundException("Customer ID not found: " + customerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
