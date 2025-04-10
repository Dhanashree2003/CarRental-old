package prj3.carRental;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Lease;
import org.junit.Test;

public class LeaseTest {

    @Test
    public void testConstructor() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2025-04-10");
        Date end = sdf.parse("2025-04-15");

        Lease lease = new Lease(1, 100, 200, start, end, "Short-term");

        assertNotNull(lease);
        assertEquals(1, lease.getLeaseID());
        assertEquals(100, lease.getVehicleID());
        assertEquals(200, lease.getCustomerID());
        assertEquals(start, lease.getStartDate());
        assertEquals(end, lease.getEndDate());
        assertEquals("Short-term", lease.getType());
    }

    @Test
    public void testGettersAndSetters() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2025-04-12");
        Date end = sdf.parse("2025-04-18");

        Lease lease = new Lease();
        lease.setLeaseID(2);
        lease.setVehicleID(101);
        lease.setCustomerID(201);
        lease.setStartDate(start);
        lease.setEndDate(end);
        lease.setType("Long-term");

        assertEquals(2, lease.getLeaseID());
        assertEquals(101, lease.getVehicleID());
        assertEquals(201, lease.getCustomerID());
        assertEquals(start, lease.getStartDate());
        assertEquals(end, lease.getEndDate());
        assertEquals("Long-term", lease.getType());
    }

    @Test
    public void testToString() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2025-04-10");
        Date end = sdf.parse("2025-04-15");

        Lease lease = new Lease(1, 100, 200, start, end, "Short-term");

        String expected = "Lease [leaseID=1, vehicleID=100, customerID=200, startDate=" 
                + start + ", endDate=" + end + ", type=Short-term]";
        assertEquals(expected, lease.toString());
    }
}
