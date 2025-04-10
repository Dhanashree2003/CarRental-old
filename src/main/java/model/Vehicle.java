package model;


public class Vehicle {
    private int vehicleID;
    private String make;
    private String model;
    private int year;
    private double dailyRate;
    private String status;
    private int passengerCapacity;
    private double engineCapacity;

    public Vehicle() {}

    public Vehicle(int vehicleID, String make, String model, int year, double dailyRate,
                   String status, int passengerCapacity, double engineCapacity) {
        this.setVehicleID(vehicleID);
        this.setMake(make);
        this.setModel(model);
        this.setYear(year);
        this.setDailyRate(dailyRate);
        this.setStatus(status);
        this.setPassengerCapacity(passengerCapacity);
        this.setEngineCapacity(engineCapacity);
    }

// Getters and Setters
    
	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public double getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(double engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

  
	

	@Override public String toString() { return "Vehicle ID: " + getVehicleID() + ", Make: " + getMake() + ", Model: " + getModel() + ", Year: " + getYear() + ", Daily Rate: " + getDailyRate() + ", Status: " + getStatus() + ", Passenger Capacity: " + getPassengerCapacity() + ", Engine Capacity: " + getEngineCapacity(); }


}
