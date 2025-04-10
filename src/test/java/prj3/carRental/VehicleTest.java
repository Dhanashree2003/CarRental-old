package prj3.carRental;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Vehicle;

public class VehicleTest {

    @Test
    public void testConstructor() {
        Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 2021, 1000.0,
                "Available", 5, 1.8);

        assertNotNull(vehicle);
        assertEquals(1, vehicle.getVehicleID());
        assertEquals("Toyota", vehicle.getMake());
        assertEquals("Corolla", vehicle.getModel());
        assertEquals(2021, vehicle.getYear());
        assertEquals(1000.0, vehicle.getDailyRate(), 0.01);
        assertEquals("Available", vehicle.getStatus());
        assertEquals(5, vehicle.getPassengerCapacity());
        assertEquals(1.8, vehicle.getEngineCapacity(), 0.01);
    }

    @Test
    public void testGettersAndSetters() {
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleID(2);
        vehicle.setMake("Hyundai");
        vehicle.setModel("i20");
        vehicle.setYear(2023);
        vehicle.setDailyRate(1200.0);
        vehicle.setStatus("Booked");
        vehicle.setPassengerCapacity(4);
        vehicle.setEngineCapacity(1.2);

        assertEquals(2, vehicle.getVehicleID());
        assertEquals("Hyundai", vehicle.getMake());
        assertEquals("i20", vehicle.getModel());
        assertEquals(2023, vehicle.getYear());
        assertEquals(1200.0, vehicle.getDailyRate(), 0.01);
        assertEquals("Booked", vehicle.getStatus());
        assertEquals(4, vehicle.getPassengerCapacity());
        assertEquals(1.2, vehicle.getEngineCapacity(), 0.01);
    }

    @Test
    public void testToString() {
        Vehicle vehicle = new Vehicle(3, "Ford", "Fiesta", 2020, 950.0,
                "Maintenance", 4, 1.6);

        String expected = "Vehicle ID: 3, Make: Ford, Model: Fiesta, Year: 2020, Daily Rate: 950.0, Status: Maintenance, Passenger Capacity: 4, Engine Capacity: 1.6";
        assertEquals(expected, vehicle.toString());
    }
}
