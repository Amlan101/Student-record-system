package dao;

import model.Assignment;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {

    private static final String INSERT_ASSIGNMENT_SQL = "INSERT INTO assignments (course_id, assignment_name, due_date, description) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ASSIGNMENT_BY_ID_SQL = "SELECT * FROM assignments WHERE assignment_id = ?";
    private static final String UPDATE_ASSIGNMENT_SQL = "UPDATE assignments SET course_id = ?, assignment_name = ?, due_date = ?, description = ? WHERE assignment_id = ?";
    private static final String DELETE_ASSIGNMENT_SQL = "DELETE FROM assignments WHERE assignment_id = ?";

    private static final String SELECT_ALL_ASSIGNMENTS_SQL = "SELECT * FROM assignments";

    public void insertAssignment(Assignment assignment) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_ASSIGNMENT_SQL);
        statement.setInt(1, assignment.getCourseId());
        statement.setString(2, assignment.getAssignmentName());
        statement.setDate(3, assignment.getDueDate() != null ? new java.sql.Date(assignment.getDueDate().getTime()) : null);
        statement.setString(4, assignment.getDescription());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public Assignment getAssignmentById(int assignmentId) throws SQLException {
        Assignment assignment = null;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ASSIGNMENT_BY_ID_SQL);
        statement.setInt(1, assignmentId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            assignment = new Assignment();
            assignment.setAssignmentId(resultSet.getInt("assignment_id"));
            assignment.setCourseId(resultSet.getInt("course_id"));
            assignment.setAssignmentName(resultSet.getString("assignment_name"));
            assignment.setDueDate(resultSet.getDate("due_date"));
            assignment.setDescription(resultSet.getString("description"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return assignment;
    }

    public void updateAssignment(Assignment assignment) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ASSIGNMENT_SQL);
        statement.setInt(1, assignment.getCourseId());
        statement.setString(2, assignment.getAssignmentName());
        statement.setDate(3, assignment.getDueDate() != null ? new java.sql.Date(assignment.getDueDate().getTime()) : null);
        statement.setString(4, assignment.getDescription());
        statement.setInt(5, assignment.getAssignmentId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteAssignment(int assignmentId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ASSIGNMENT_SQL);
        statement.setInt(1, assignmentId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public List<Assignment> getAllAssignments() throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ASSIGNMENTS_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentId(resultSet.getInt("assignment_id"));
                assignment.setCourseId(resultSet.getInt("course_id"));
                assignment.setAssignmentName(resultSet.getString("assignment_name"));
                assignment.setDueDate(resultSet.getDate("due_date"));
                assignment.setDescription(resultSet.getString("description"));
                assignments.add(assignment);
            }
        }
        return assignments;
    }
}
