package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String connDetails = DBPropertyUtil.getConnectionString();
        String[] parts = connDetails.split(";");

        String driver = parts[0];
        String url = parts[1];
        String user = parts[2];
        String password = parts[3];

        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}


