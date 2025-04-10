package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Exception.*;
import model.Lease;
import util.DBConnUtil;

public class LeaseDaoImpl implements LeaseDao {
	
	private Connection connection;     
    public LeaseDaoImpl() {
   	    try {
   	        connection = DBConnUtil.getConnection();
   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
   	}

    
    
    @Override
    public Lease createLease(int customerID, int carID, Date startDate, Date endDate) {
    	
    	try {
    		 //Validate Customer if exists
            CustomerDao customerDao = new CustomerDaoImpl();
            customerDao.findCustomerById(customerID); 

            //Validate Vehicle if exists
            VehicleDao vehicleDao = new VehicleDaoImpl();
            vehicleDao.findCarById(carID); 
    	
        String sql = "INSERT INTO Lease (vehicleID, customerID, startDate, endDate, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            long duration = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
            String leaseType = duration <= 30 ? "DailyLease" : "MonthlyLease";

            stmt.setInt(1, carID);
            stmt.setInt(2, customerID);
            stmt.setDate(3, new java.sql.Date(startDate.getTime()));
            stmt.setDate(4, new java.sql.Date(endDate.getTime()));
            stmt.setString(5, leaseType);

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int leaseID = rs.getInt(1);
                updateCarStatus(carID, "notAvailable");
                return new Lease(leaseID, carID, customerID, startDate, endDate, leaseType);
            }
        }
    }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void returnCar(int leaseID) {
        String sql = "SELECT vehicleID FROM Lease WHERE leaseID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, leaseID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int carID = rs.getInt("vehicleID");
                updateCarStatus(carID, "available");
            } else {
                throw new LeaseNotFoundException("Lease ID not found: " + leaseID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lease> listActiveLeases() {
        List<Lease> leases = new ArrayList<>();
        String sql = "SELECT * FROM Lease WHERE endDate >= CURDATE()";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                leases.add(mapLease(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }

    @Override
    public List<Lease> listLeaseHistory() {
        List<Lease> leases = new ArrayList<>();
        String sql = "SELECT * FROM Lease";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                leases.add(mapLease(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }

    private Lease mapLease(ResultSet rs) throws SQLException {
        return new Lease(
            rs.getInt("leaseID"),
            rs.getInt("vehicleID"),
            rs.getInt("customerID"),
            rs.getDate("startDate"),
            rs.getDate("endDate"),
            rs.getString("type")
        );
    }

    private void updateCarStatus(int carID, String status) {
        String sql = "UPDATE Vehicle SET status = ? WHERE vehicleID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, carID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



	

    
}
