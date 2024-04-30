package dao;

import model.Grade;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDAO {

    private static final String INSERT_GRADE_SQL = "INSERT INTO grades (enrollment_id, assignment_id, score, letter_grade) VALUES (?, ?, ?, ?)";
    private static final String SELECT_GRADE_BY_ID_SQL = "SELECT * FROM grades WHERE grade_id = ?";
    private static final String UPDATE_GRADE_SQL = "UPDATE grades SET enrollment_id = ?, assignment_id = ?, score = ?, letter_grade = ? WHERE grade_id = ?";
    private static final String DELETE_GRADE_SQL = "DELETE FROM grades WHERE grade_id = ?";

    public void insertGrade(Grade grade) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_GRADE_SQL);
        statement.setInt(1, grade.getEnrollmentId());
        statement.setInt(2, grade.getAssignmentId());
        statement.setDouble(3, grade.getCgpa());
        statement.setString(4, grade.getLetterGrade());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public Grade getGradeById(int gradeId) throws SQLException {
        Grade grade = null;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_GRADE_BY_ID_SQL);
        statement.setInt(1, gradeId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            grade = new Grade();
            grade.setGradeId(resultSet.getInt("grade_id"));
            grade.setEnrollmentId(resultSet.getInt("enrollment_id"));
            grade.setAssignmentId(resultSet.getInt("assignment_id"));
            grade.setCgpa(resultSet.getDouble("score"));
            grade.setLetterGrade(resultSet.getString("letter_grade"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return grade;
    }

    public void updateGrade(Grade grade) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_GRADE_SQL);
        statement.setInt(1, grade.getEnrollmentId());
        statement.setInt(2, grade.getAssignmentId());
        statement.setDouble(3, grade.getCgpa());
        statement.setString(4, grade.getLetterGrade());
        statement.setInt(5, grade.getGradeId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteGrade(int gradeId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_GRADE_SQL);
        statement.setInt(1, gradeId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
