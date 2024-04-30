package dao;

import model.Department;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO {

    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO departments (department_name) VALUES (?)";
    private static final String SELECT_DEPARTMENT_BY_ID_SQL = "SELECT * FROM departments WHERE department_id = ?";
    private static final String UPDATE_DEPARTMENT_SQL = "UPDATE departments SET department_name = ? WHERE department_id = ?";
    private static final String DELETE_DEPARTMENT_SQL = "DELETE FROM departments WHERE department_id = ?";

    public void insertDepartment(Department department) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT_SQL);
        statement.setString(1, department.getDepartmentName());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
        Department department = null;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID_SQL);
        statement.setInt(1, departmentId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            department = new Department();
            department.setDepartmentId(resultSet.getInt("department_id"));
            department.setDepartmentName(resultSet.getString("department_name"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return department;
    }

    public void updateDepartment(Department department) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMENT_SQL);
        statement.setString(1, department.getDepartmentName());
        statement.setInt(2, department.getDepartmentId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteDepartment(int departmentId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT_SQL);
        statement.setInt(1, departmentId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}

