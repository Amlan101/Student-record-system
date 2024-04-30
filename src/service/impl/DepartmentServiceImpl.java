package service.impl;

import dao.DepartmentDAO;
import exceptions.ServiceException;
import model.Department;
import service.DepartmentService;

import java.sql.SQLException;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public void createDepartment(Department department) throws ServiceException {
        try {
            departmentDAO.insertDepartment(department);
        } catch (SQLException e) {
            throw new ServiceException("Error creating department: " + e.getMessage(), e);
        }
    }

    @Override
    public Department getDepartmentById(int departmentId) throws ServiceException {
        try {
            return departmentDAO.getDepartmentById(departmentId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving department: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateDepartment(Department department) throws ServiceException {
        try {
            departmentDAO.updateDepartment(department);
        } catch (SQLException e) {
            throw new ServiceException("Error updating department: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteDepartment(int departmentId) throws ServiceException {
        try {
            departmentDAO.deleteDepartment(departmentId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting department: " + e.getMessage(), e);
        }
    }
}