package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Vehicle;
import Exception.CarNotFoundException;
import util.DBConnUtil;

public class VehicleDaoImpl implements VehicleDao {

    Connection connection;

    @Override
    public void addCar(Vehicle car) throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        String sql = "INSERT INTO Vehicle (vehicleID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, car.getVehicleID());
        pst.setString(2, car.getMake());
        pst.setString(3, car.getModel());
        pst.setInt(4, car.getYear());
        pst.setDouble(5, car.getDailyRate());
        pst.setString(6, car.getStatus());
        pst.setInt(7, car.getPassengerCapacity());
        pst.setDouble(8, car.getEngineCapacity());
        pst.executeUpdate();
    }

    @Override
    public void removeCar(int carID) throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        String sql = "DELETE FROM Vehicle WHERE vehicleID = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, carID);
        int rows = pst.executeUpdate();
        if (rows == 0) {
            throw new CarNotFoundException("Car ID not found: " + carID);
        }
    }

    @Override
    public List<Vehicle> listAvailableCars() throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        List<Vehicle> cars = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE status = 'available'";
        Statement pst = connection.createStatement();
        ResultSet rs = pst.executeQuery(sql);
        while (rs.next()) {
            cars.add(mapVehicle(rs));
        }
        return cars;
    }

    @Override
    public List<Vehicle> listRentedCars() throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        List<Vehicle> cars = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE status = 'notAvailable'";
        Statement pst = connection.createStatement();
        ResultSet rs = pst.executeQuery(sql);
        while (rs.next()) {
            cars.add(mapVehicle(rs));
        }
        return cars;
    }

    @Override
    public Vehicle findCarById(int carID) throws SQLException,ClassNotFoundException {
    	connection = DBConnUtil.getConnection();
        String sql = "SELECT * FROM Vehicle WHERE vehicleID = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, carID);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return mapVehicle(rs);
        } else {
            throw new CarNotFoundException("Car ID not found: " + carID);
        }
    }

    private Vehicle mapVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
            rs.getInt("vehicleID"),
            rs.getString("make"),
            rs.getString("model"),
            rs.getInt("year"),
            rs.getDouble("dailyRate"),
            rs.getString("status"),
            rs.getInt("passengerCapacity"),
            rs.getDouble("engineCapacity")
        );
    }
}
