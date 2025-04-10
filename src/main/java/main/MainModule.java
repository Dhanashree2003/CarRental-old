package main;


import dao.CustomerDao;
import dao.CustomerDaoImpl;
import dao.VehicleDao;
import dao.VehicleDaoImpl;
import dao.PaymentDao;
import dao.PaymentDaoImpl;
import dao.LeaseDao;
import dao.LeaseDaoImpl;
import model.Payment;
import model.Customer;
import model.Lease;
import model.Vehicle;
import Exception.CarNotFoundException;
import Exception.CustomerNotFoundException;
import Exception.LeaseNotFoundException;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;


public class MainModule {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VehicleDao vehicle = new VehicleDaoImpl();
        CustomerDao customer = new CustomerDaoImpl();
        LeaseDao lease1 = new LeaseDaoImpl();
        PaymentDao payment = new PaymentDaoImpl();

        while (true) {
            System.out.println("\n========== Car Rental System ==========");
            System.out.println("Car Management ------> 1 to 5 ");
            System.out.println("Customer Management -> 6 to 9 ");
            System.out.println("Lease Management ----> 10 to 13 ");
            System.out.println("Payment Managemnet --> 14 ");
            System.out.println("=======================================\n");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. List Available Cars");
            System.out.println("4. List Rented Cars");
            System.out.println("5. Find Car by ID");
            System.out.println("6. Add Customer");
            System.out.println("7. Remove Customer");
            System.out.println("8. List Customers");
            System.out.println("9. Find Customer by ID");
            System.out.println("10. Create Lease");
            System.out.println("11. Return Car");
            System.out.println("12. List Active Leases");
            System.out.println("13. List Lease History");
            System.out.println("14. Record Payment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        Vehicle car = new Vehicle();
                        System.out.print("Enter Vehicle ID: ");
                        car.setVehicleID(sc.nextInt());
                        sc.nextLine();
                        System.out.print("Make: ");
                        car.setMake(sc.nextLine());
                        System.out.print("Model: ");
                        car.setModel(sc.nextLine());
                        System.out.print("Year: ");
                        car.setYear(sc.nextInt());
                        System.out.print("Daily Rate: ");
                        car.setDailyRate(sc.nextDouble());
                        sc.nextLine();
                        System.out.print("Status (available/notAvailable): ");
                        car.setStatus(sc.nextLine());
                        System.out.print("Passenger Capacity: ");
                        car.setPassengerCapacity(sc.nextInt());
                        System.out.print("Engine Capacity: ");
                        car.setEngineCapacity(sc.nextDouble());
                        vehicle.addCar(car);
                        System.out.println("Car added successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Car ID to remove: ");
                        vehicle.removeCar(sc.nextInt());
                        System.out.println("Car removed.");
                        break;

                    case 3:
                        vehicle.listAvailableCars().forEach(System.out::println);
                        break;

                    case 4:
                        vehicle.listRentedCars().forEach(System.out::println);
                        break;

                    case 5:
                        System.out.print("Enter Car ID to find: ");
                        System.out.println(vehicle.findCarById(sc.nextInt()));
                        break;

                    case 6:
                        Customer cust = new Customer();
                        System.out.print("Customer ID: ");
                        cust.setCustomerID(sc.nextInt());
                        sc.nextLine();
                        System.out.print("First Name: ");
                        cust.setFirstName(sc.nextLine());
                        System.out.print("Last Name: ");
                        cust.setLastName(sc.nextLine());
                        System.out.print("Email: ");
                        cust.setEmail(sc.nextLine());
                        System.out.print("Phone Number: ");
                        cust.setPhoneNumber(sc.nextLine());
                        customer.addCustomer(cust);
                        System.out.println("Customer added.");
                        break;

                    case 7:
                        System.out.print("Enter Customer ID to remove: ");
                        customer.removeCustomer(sc.nextInt());
                        System.out.println("Customer removed.");
                        break;

                    case 8:
                        customer.listCustomers().forEach(System.out::println);
                        break;

                    case 9:
                        System.out.print("Enter Customer ID to find: ");
                        System.out.println(customer.findCustomerById(sc.nextInt()));
                        break;

                    case 10:
                        System.out.print("Customer ID: ");
                        int cid = sc.nextInt();
                        System.out.print("Car ID: ");
                        int vid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Start Date (yyyy-mm-dd): ");
                        Date start = Date.valueOf(sc.nextLine());
                        System.out.print("End Date (yyyy-mm-dd): ");
                        Date end = Date.valueOf(sc.nextLine());
                        
                        lease1.createLease(cid, vid, start, end);
                        System.out.println("Lease created.");
                        break;

                   // case 11:
//                        System.out.print("Enter Lease ID to return car: ");
//                        repo.returnCar(sc.nextInt());
                       // System.out.println("Car returned.");
                    	
                    case 11:
                        System.out.print("Enter Lease ID to return car: ");
                        int leaseID = sc.nextInt();
                        sc.nextLine(); // consume newline
                        lease1.returnCar(leaseID); // prints car info inside
                        break;

                    	
                        
                       

                    case 12:
                        lease1.listActiveLeases().forEach(System.out::println);
                        break;

                    case 13:
                        lease1.listLeaseHistory().forEach(System.out::println);
                        break;

                    case 14:
                        System.out.print("Enter Lease ID: ");
                        int leaseId = sc.nextInt();
                        System.out.print("Enter Payment Amount: ");
                        double amt = sc.nextDouble();
                        Lease lease = new Lease();
                        lease.setLeaseID(leaseId);
                        payment.recordPayment(lease, amt);
                        System.out.println("Payment recorded.");
                        break;

                        
                    case 0:
                        System.out.println("Exiting application.");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("❗ Error: " + e.getMessage());
            }
        }
    }
}

