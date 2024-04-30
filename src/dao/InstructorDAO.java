package dao;

import model.Instructor;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorDAO {

    private static final String INSERT_INSTRUCTOR_SQL = "INSERT INTO instructors (instructor_name, department_id) VALUES (?, ?)";
    private static final String SELECT_INSTRUCTOR_BY_ID_SQL = "SELECT * FROM instructors WHERE instructor_id = ?";
    private static final String UPDATE_INSTRUCTOR_SQL = "UPDATE instructors SET instructor_name = ?, department_id = ? WHERE instructor_id = ?";
    private static final String DELETE_INSTRUCTOR_SQL = "DELETE FROM instructors WHERE instructor_id = ?";

    public void insertInstructor(Instructor instructor) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement;
        // Construct full name if separate first and last name are used
        String fullName = instructor.getFirstName() != null && instructor.getLastName() != null
                ? instructor.getFirstName() + " " + instructor.getLastName() : instructor.getFirstName();
        statement = connection.prepareStatement(INSERT_INSTRUCTOR_SQL);
        statement.setString(1, fullName);
        statement.setInt(2, instructor.getDepartmentId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public Instructor getInstructorById(int instructorId) throws SQLException {
        Instructor instructor = null;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_INSTRUCTOR_BY_ID_SQL);
        statement.setInt(1, instructorId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            instructor = new Instructor();
            instructor.setInstructorId(resultSet.getInt("instructor_id"));
            instructor.setFirstName(resultSet.getString("first_name"));
            instructor.setLastName(resultSet.getString("last_name"));
            instructor.setDepartmentId(resultSet.getInt("department_id"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return instructor;
    }

    public void updateInstructor(Instructor instructor) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement;
        // Construct full name if separate first and last name are used
        String fullName = instructor.getFirstName() != null && instructor.getLastName() != null
                ? instructor.getFirstName() + " " + instructor.getLastName() : instructor.getFirstName();
        statement = connection.prepareStatement(UPDATE_INSTRUCTOR_SQL);
        statement.setString(1, fullName);
        statement.setInt(2, instructor.getDepartmentId());
        statement.setInt(3, instructor.getInstructorId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteInstructor(int instructorId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_INSTRUCTOR_SQL);
        statement.setInt(1, instructorId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
