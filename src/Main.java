import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        testDatabaseConnection();
    }

    private static void testDatabaseConnection() {
        Connection connection = null;
        try {
            // Attempt to get a connection from the DatabaseConnection class
            connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }
}