package dao;

import java.util.List;

import model.Vehicle;

public interface VehicleDao {
	// Car Management
    void addCar(Vehicle car);
    void removeCar(int carID);
    List<Vehicle> listAvailableCars();
    List<Vehicle> listRentedCars();
    Vehicle findCarById(int carID);

}
