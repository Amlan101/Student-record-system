package dao;

import model.Enrollment;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentDAO {

    private static final String INSERT_ENROLLMENT_SQL = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
    private static final String SELECT_ENROLLMENT_BY_ID_SQL = "SELECT * FROM enrollments WHERE enrollment_id = ?";
    private static final String UPDATE_ENROLLMENT_SQL = "UPDATE enrollments SET student_id = ?, course_id = ? WHERE enrollment_id = ?";
    private static final String DELETE_ENROLLMENT_SQL = "DELETE FROM enrollments WHERE enrollment_id = ?";

    public void insertEnrollment(Enrollment enrollment) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_ENROLLMENT_SQL);
        statement.setInt(1, enrollment.getStudentId());
        statement.setInt(2, enrollment.getCourseId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public Enrollment getEnrollmentById(int enrollmentId) throws SQLException {
        Enrollment enrollment = null;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ENROLLMENT_BY_ID_SQL);
        statement.setInt(1, enrollmentId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            enrollment = new Enrollment();
            enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));
            enrollment.setStudentId(resultSet.getInt("student_id"));
            enrollment.setCourseId(resultSet.getInt("course_id"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return enrollment;
    }

    public void updateEnrollment(Enrollment enrollment) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ENROLLMENT_SQL);
        statement.setInt(1, enrollment.getStudentId());
        statement.setInt(2, enrollment.getCourseId());
        statement.setInt(3, enrollment.getEnrollmentId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteEnrollment(int enrollmentId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ENROLLMENT_SQL);
        statement.setInt(1, enrollmentId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
