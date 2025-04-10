package model;


import java.util.Date;

public class Lease {
    private int leaseID;
    private int vehicleID;
    private int customerID;
    private Date startDate;
    private Date endDate;
    private String type;

    public Lease() {}

    public Lease(int leaseID, int vehicleID, int customerID, Date startDate, Date endDate, String type) {
        this.setLeaseID(leaseID);
        this.setVehicleID(vehicleID);
        this.setCustomerID(customerID);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setType(type);
    }

	public int getLeaseID() {
		return leaseID;
	}

	public void setLeaseID(int leaseID) {
		this.leaseID = leaseID;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Lease [leaseID=" + leaseID + ", vehicleID=" + vehicleID + ", customerID=" + customerID + ", startDate="
				+ startDate + ", endDate=" + endDate + ", type=" + type + "]";
	}

    // Getters and Setters
	
	
	
}
