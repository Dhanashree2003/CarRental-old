package dao;

import java.util.Date;
import java.util.List;

import model.Lease;

public interface LeaseDao {
	// Lease Management
    Lease createLease(int customerID, int carID, Date startDate, Date endDate);
    void returnCar(int leaseID);
    List<Lease> listActiveLeases();
    List<Lease> listLeaseHistory();

}
