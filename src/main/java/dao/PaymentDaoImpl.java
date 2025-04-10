package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Lease;
import util.DBConnUtil;


public class PaymentDaoImpl implements PaymentDao{
	
	private Connection connection;     
    public PaymentDaoImpl() {
   	    try {
   	        connection = DBConnUtil.getConnection();
   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
   	}
    
    
    @Override
    public void recordPayment(Lease lease, double amount) {
        String sql = "INSERT INTO Payment (leaseID, paymentDate, amount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, lease.getLeaseID());
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





	@Override
	public Lease createLease(int customerID, int carID, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

  
    
    

}
