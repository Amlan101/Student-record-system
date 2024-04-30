package dao;

import model.Student;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String INSERT_STUDENT_SQL = "INSERT INTO students (first_name, last_name, email, date_of_birth) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID_SQL = "SELECT * FROM students WHERE student_id = ?";
    private static final String UPDATE_STUDENT_SQL = "UPDATE students SET first_name = ?, last_name = ?, email = ?, date_of_birth = ? WHERE student_id = ?";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM students WHERE student_id = ?";
    private static final String SELECT_ALL_STUDENTS_SQL = "SELECT * FROM students";

    public Student insertStudent(Student student) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT_SQL);
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getEmail());
        statement.setDate(4, new java.sql.Date(student.getDateOfBirth().getTime()));  // Convert Java Date to SQL Date
        statement.executeUpdate();
        statement.close();
        connection.close();
        return student;
    }

    public Student getStudentById(int studentId) throws SQLException {
        Student student = null;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_ID_SQL);
        statement.setInt(1, studentId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            student = new Student();
            student.setStudentId(resultSet.getInt("student_id"));
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setEmail(resultSet.getString("email"));
            student.setDateOfBirth(new java.util.Date(resultSet.getDate("date_of_birth").getTime()));  // Convert SQL Date to Java Date
        }
        resultSet.close();
        statement.close();
        connection.close();
        return student;
    }

    public Student updateStudent(Student student) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getEmail());
        statement.setDate(4, new java.sql.Date(student.getDateOfBirth().getTime()));  // Convert Java Date to SQL Date
        statement.setInt(5, student.getStudentId());
        statement.executeUpdate();
        statement.close();
        connection.close();
        return student;
    }

    public void deleteStudent(int studentId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);
        statement.setInt(1, studentId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    // Method to retrieve all student records
    public List<Student> getAllStudents() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Student student = extractStudentFromResultSet(resultSet);
                studentList.add(student);
            }
        }
        return studentList;
    }
    // Helper method to extract Student object from ResultSet
    private Student extractStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getInt("student_id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setEmail(resultSet.getString("email"));
        student.setDateOfBirth(new java.util.Date(resultSet.getDate("date_of_birth").getTime()));
        return student;
    }

}
