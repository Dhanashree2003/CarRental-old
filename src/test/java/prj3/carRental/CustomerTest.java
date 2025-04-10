package prj3.carRental;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Customer;

public class CustomerTest {

    @Test
    public void testConstructor() {
        Customer customer = new Customer();
        assertNotNull(customer);

        Customer customer2 = new Customer(101, "Alice", "Smith", "alice@example.com", "9876543210");
        assertEquals(101, customer2.getCustomerID());
        assertEquals("Alice", customer2.getFirstName());
        assertEquals("Smith", customer2.getLastName());
        assertEquals("alice@example.com", customer2.getEmail());
        assertEquals("9876543210", customer2.getPhoneNumber());
    }

    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();
        customer.setCustomerID(202);
        customer.setFirstName("Bob");
        customer.setLastName("Johnson");
        customer.setEmail("bob@example.com");
        customer.setPhoneNumber("1234567890");

        assertEquals(202, customer.getCustomerID());
        assertEquals("Bob", customer.getFirstName());
        assertEquals("Johnson", customer.getLastName());
        assertEquals("bob@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhoneNumber());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(303, "Charlie", "Brown", "charlie@example.com", "1122334455");
        String expected = "Customer [customerID=303, firstName=Charlie, lastName=Brown, email=charlie@example.com, phoneNumber=1122334455]";
        assertEquals(expected, customer.toString());
    }
}
