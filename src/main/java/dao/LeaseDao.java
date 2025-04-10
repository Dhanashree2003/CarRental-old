package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Lease;
import model.Vehicle;

public interface LeaseDao {
	// Lease Management
    Lease createLease(int customerID, int carID, Date startDate, Date endDate) throws ClassNotFoundException, SQLException;
    void returnCar(int leaseID) throws ClassNotFoundException, SQLException;
    List<Lease> listActiveLeases() throws ClassNotFoundException, SQLException;
    List<Lease> listLeaseHistory() throws ClassNotFoundException, SQLException;

}
