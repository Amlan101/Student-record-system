package dao;


import model.Attendance;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    // SQL queries for CRUD operations
    private static final String INSERT_ATTENDANCE = "INSERT INTO attendance (enrollment_id, attendance_date, present) VALUES (?, ?, ?)";
    private static final String SELECT_ATTENDANCE_BY_ID = "SELECT * FROM attendance WHERE attendance_id = ?";
    private static final String UPDATE_ATTENDANCE = "UPDATE attendance SET enrollment_id = ?, attendance_date = ?, present = ? WHERE attendance_id = ?";
    private static final String DELETE_ATTENDANCE = "DELETE FROM attendance WHERE attendance_id = ?";
    private static final String SELECT_ALL_ATTENDANCE = "SELECT * FROM attendance";


    // Method to insert a new attendance record
    public boolean insertAttendance(Attendance attendance) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTENDANCE)) {
            preparedStatement.setInt(1, attendance.getEnrollmentId());
            preparedStatement.setDate(2, new Date(attendance.getAttendanceDate().getTime()));
            preparedStatement.setBoolean(3, attendance.isPresent());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting attendance record: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve an attendance record by its ID
    public Attendance getAttendanceById(int attendanceId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTENDANCE_BY_ID)) {
            preparedStatement.setInt(1, attendanceId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractAttendanceFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving attendance record by ID: " + e.getMessage());
        }
        return null;
    }

    // Method to update an existing attendance record
    public boolean updateAttendance(Attendance attendance) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ATTENDANCE)) {
            preparedStatement.setInt(1, attendance.getEnrollmentId());
            preparedStatement.setDate(2, new Date(attendance.getAttendanceDate().getTime()));
            preparedStatement.setBoolean(3, attendance.isPresent());
            preparedStatement.setInt(4, attendance.getAttendanceId());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Error updating attendance record: " + e.getMessage());
            return false;
        }
    }

    // Method to delete an existing attendance record
    public boolean deleteAttendance(int attendanceId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ATTENDANCE)) {
            preparedStatement.setInt(1, attendanceId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting attendance record: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve all attendance records
    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ATTENDANCE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Attendance attendance = extractAttendanceFromResultSet(resultSet);
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all attendance records: " + e.getMessage());
        }
        return attendanceList;
    }

    // Helper method to extract Attendance object from ResultSet
    private Attendance extractAttendanceFromResultSet(ResultSet resultSet) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(resultSet.getInt("attendance_id"));
        attendance.setEnrollmentId(resultSet.getInt("enrollment_id"));
        attendance.setAttendanceDate(resultSet.getDate("attendance_date"));
        attendance.setPresent(resultSet.getBoolean("present"));
        return attendance;
    }
}

