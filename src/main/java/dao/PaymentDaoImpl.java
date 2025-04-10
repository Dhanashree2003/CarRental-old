package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Exception.*;

import model.Lease;
import util.DBConnUtil;

public class PaymentDaoImpl implements PaymentDao {

   Connection connection;

    @Override
    public void recordPayment(Lease lease, double amount) throws ClassNotFoundException,SQLException {
    	connection = DBConnUtil.getConnection();
        String sql = "INSERT INTO Payment (leaseID, paymentDate, amount) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, lease.getLeaseID());
        stmt.setDate(2, new Date(System.currentTimeMillis()));
        stmt.setDouble(3, amount);
        stmt.executeUpdate();
    }

    @Override
    public Lease createLease(int customerID, int carID, Date startDate, Date endDate) {
        // You can implement this if needed
        return null;
    }
}

    

