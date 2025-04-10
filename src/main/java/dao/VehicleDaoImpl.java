package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Vehicle;
import Exception.CarNotFoundException;
import util.DBConnUtil;

public class VehicleDaoImpl implements VehicleDao {
	
	private Connection connection;     
    public VehicleDaoImpl() {
   	    try {
   	        connection = DBConnUtil.getConnection();
   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
   	}
	
	@Override
    public void addCar(Vehicle car) {
        String sql = "INSERT INTO Vehicle (vehicleID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, car.getVehicleID());
            stmt.setString(2, car.getMake());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setDouble(5, car.getDailyRate());
            stmt.setString(6, car.getStatus());
            stmt.setInt(7, car.getPassengerCapacity());
            stmt.setDouble(8, car.getEngineCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCar(int carID) {
        String sql = "DELETE FROM Vehicle WHERE vehicleID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carID);
            int rows = stmt.executeUpdate();
            if (rows == 0) throw new CarNotFoundException("Car ID not found: " + carID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vehicle> listAvailableCars() {
        List<Vehicle> cars = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE status = 'available'";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(mapVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Vehicle> listRentedCars() {
        List<Vehicle> cars = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE status = 'notAvailable'";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(mapVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Vehicle findCarById(int carID) {
        String sql = "SELECT * FROM Vehicle WHERE vehicleID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapVehicle(rs);
            } else {
                throw new CarNotFoundException("Car ID not found: " + carID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
