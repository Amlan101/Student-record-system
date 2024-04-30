package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_records";
    private static final String USER = "root";
    private static final String PASSWORD = "@rgentuM101";

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found");
            e.printStackTrace();
        }
        return connection;
    }
}
