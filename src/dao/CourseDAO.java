package dao;

import model.Course;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private static final String INSERT_COURSE_SQL = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";
    private static final String SELECT_COURSE_BY_ID_SQL = "SELECT * FROM courses WHERE course_id = ?";
    private static final String SELECT_ALL_COURSES = "SELECT * FROM courses";
    private static final String UPDATE_COURSE_SQL = "UPDATE courses SET course_name = ?, course_description = ? WHERE course_id = ?";
    private static final String DELETE_COURSE_SQL = "DELETE FROM courses WHERE course_id = ?";

    // Method to insert a new course record
    public void insertCourse(Course course) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_COURSE_SQL);
        statement.setString(1, course.getCourseName());
        statement.setString(2, course.getCourseDescription());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Method to retrieve a course record by its ID
    public Course getCourseById(int courseId) throws SQLException {
        Course course = null;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_COURSE_BY_ID_SQL);
        statement.setInt(1, courseId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            course = new Course();
            course.setCourseId(resultSet.getInt("course_id"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setCourseDescription(resultSet.getString("course_description"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return course;
    }

    // Method to update an existing course record
    public void updateCourse(Course course) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_COURSE_SQL);
        statement.setString(1, course.getCourseName());
        statement.setString(2, course.getCourseDescription());
        statement.setInt(3, course.getCourseId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Method to delete an existing course record
    public void deleteCourse(int courseId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_COURSE_SQL);
        statement.setInt(1, courseId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Method to retrieve all course records
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Course course = extractCourseFromResultSet(resultSet);
                courseList.add(course);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all course records: " + e.getMessage());
        }
        return courseList;
    }

    // Helper method to extract Course object from ResultSet
    private Course extractCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getInt("course_id"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setCourseDescription(resultSet.getString("course_description"));
        return course;
    }
}
