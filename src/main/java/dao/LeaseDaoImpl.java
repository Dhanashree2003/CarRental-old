package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Exception.LeaseNotFoundException;
import model.Lease;
import util.DBConnUtil;

public class LeaseDaoImpl implements LeaseDao {

    Connection connection;
    PreparedStatement pst;

    @Override
    public Lease createLease(int customerID, int carID, Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.findCustomerById(customerID);

        VehicleDao vehicleDao = new VehicleDaoImpl();
        vehicleDao.findCarById(carID);
        
        Date today = new Date();
        Date start = new Date(startDate.getTime());
        Date end = new Date(endDate.getTime());

        if (start.before(today)) {
            System.out.println("Lease Start Date cannot be in the past.");
            return null;
        } else if (end.before(today)) {
            System.out.println("Lease End Date cannot be in the past.");
            return null;
        } else if (end.before(start)) {
            System.out.println("Lease Start Date cannot be after Lease End Date.");
            return null;
        }
       
        connection = DBConnUtil.getConnection();

        long duration = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
        String leaseType = duration <= 30 ? "DailyLease" : "MonthlyLease";

        String cmd = "INSERT INTO Lease (vehicleID, customerID, startDate, endDate, type) VALUES (?, ?, ?, ?, ?)";
        pst = connection.prepareStatement(cmd, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, carID);
        pst.setInt(2, customerID);
        pst.setDate(3, new java.sql.Date(startDate.getTime()));
        pst.setDate(4, new java.sql.Date(endDate.getTime()));
        pst.setString(5, leaseType);
        pst.executeUpdate();
        
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            int leaseID = rs.getInt(1);
            updateCarStatus(carID, "notAvailable");
            Lease lease = new Lease();
            lease.setLeaseID(leaseID);
            lease.setVehicleID(carID);
            lease.setCustomerID(customerID);
            lease.setStartDate(startDate);
            lease.setEndDate(endDate);
            lease.setType(leaseType);
            return lease;
        }
        return null;
    }

    @Override
    public void returnCar(int leaseID) throws ClassNotFoundException, SQLException {
        connection = DBConnUtil.getConnection();
        String cmd = "SELECT vehicleID FROM Lease WHERE leaseID = ?";
        pst = connection.prepareStatement(cmd);
        pst.setInt(1, leaseID);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int carID = rs.getInt("vehicleID");
            updateCarStatus(carID, "available");
        } else {
            throw new LeaseNotFoundException("Lease ID not found: " + leaseID);
        }
    }

    @Override
    public List<Lease> listActiveLeases() throws ClassNotFoundException, SQLException {
        List<Lease> leaseList = new ArrayList<>();
        connection = DBConnUtil.getConnection();
        String cmd = "SELECT * FROM Lease WHERE endDate >= CURDATE()";
        pst = connection.prepareStatement(cmd);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Lease lease = mapLease(rs);
            leaseList.add(lease);
        }
        return leaseList;
    }

    @Override
    public List<Lease> listLeaseHistory() throws ClassNotFoundException, SQLException {
        List<Lease> leaseList = new ArrayList<>();
        connection = DBConnUtil.getConnection();
        String cmd = "SELECT * FROM Lease";
        pst = connection.prepareStatement(cmd);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Lease lease = mapLease(rs);
            leaseList.add(lease);
        }
        return leaseList;
    }

    private Lease mapLease(ResultSet rs) throws SQLException {
        Lease lease = new Lease();
        lease.setLeaseID(rs.getInt("leaseID"));
        lease.setVehicleID(rs.getInt("vehicleID"));
        lease.setCustomerID(rs.getInt("customerID"));
        lease.setStartDate(rs.getDate("startDate"));
        lease.setEndDate(rs.getDate("endDate"));
        lease.setType(rs.getString("type"));
        return lease;
    }

    private void updateCarStatus(int carID, String status) throws ClassNotFoundException, SQLException {
        connection = DBConnUtil.getConnection();
        String cmd = "UPDATE Vehicle SET status = ? WHERE vehicleID = ?";
        pst = connection.prepareStatement(cmd);
        pst.setString(1, status);
        pst.setInt(2, carID);
        pst.executeUpdate();
    }
}
