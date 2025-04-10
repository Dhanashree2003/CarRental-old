package util;

import java.util.ResourceBundle;

public class DBPropertyUtil {

    public static String getConnectionString() {
        ResourceBundle rb = ResourceBundle.getBundle("carrental"); // hardcoded
        String driver = rb.getString("driver");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        return driver + ";" + url + ";" + user + ";" + password;
    }
}
