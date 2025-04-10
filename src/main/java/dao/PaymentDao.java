package dao;

import model.Lease;

public interface PaymentDao {
	
	    // Payment Handling
    
		void recordPayment(Lease lease, double amount);
		Lease createLease(int customerID, int carID, java.sql.Date startDate, java.sql.Date endDate);

}
