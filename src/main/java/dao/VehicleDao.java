package dao;

import java.sql.SQLException;
import java.util.List;

import model.Vehicle;

public interface VehicleDao {
	// Car Management
    void addCar(Vehicle car) throws SQLException, ClassNotFoundException;
    void removeCar(int carID) throws SQLException, ClassNotFoundException;
    List<Vehicle> listAvailableCars() throws SQLException, ClassNotFoundException;
    List<Vehicle> listRentedCars() throws SQLException, ClassNotFoundException;
    Vehicle findCarById(int carID) throws SQLException, ClassNotFoundException;

}
