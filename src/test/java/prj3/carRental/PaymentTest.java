package prj3.carRental;

import static org.junit.Assert.*;
import java.util.Date;
import model.Payment;
import org.junit.Test;

public class PaymentTest {

    @Test
    public void testConstructor() {
        Date date = new Date(); // using current date for test
        Payment payment = new Payment(1, 101, date, 1500.75);

        assertNotNull(payment);
        assertEquals(1, payment.getPaymentID());
        assertEquals(101, payment.getLeaseID());
        assertEquals(date, payment.getPaymentDate());
        assertEquals(1500.75, payment.getAmount(), 0.01);
    }

    @Test
    public void testGettersAndSetters() {
        Date date = new Date(); // current date for test
        Payment payment = new Payment();

        payment.setPaymentID(2);
        payment.setLeaseID(202);
        payment.setPaymentDate(date);
        payment.setAmount(3200.00);

        assertEquals(2, payment.getPaymentID());
        assertEquals(202, payment.getLeaseID());
        assertEquals(date, payment.getPaymentDate());
        assertEquals(3200.00, payment.getAmount(), 0.01);
    }

    @Test
    public void testToString() {
        Date date = new Date();
        Payment payment = new Payment(3, 303, date, 500.00);

        String expected = "Payment [paymentID=3, leaseID=303, paymentDate=" + date + ", amount=500.0]";
        assertEquals(expected, payment.toString());
    }
}
