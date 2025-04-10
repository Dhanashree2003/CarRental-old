package dao;

import java.sql.SQLException;

import model.Lease;

public interface PaymentDao {
	
	    // Payment Handling
    
		void recordPayment(Lease lease, double amount) throws ClassNotFoundException, SQLException;
		Lease createLease(int customerID, int carID, java.sql.Date startDate, java.sql.Date endDate);

}
