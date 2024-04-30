package service;

import exceptions.ServiceException;
import model.Department;

public interface DepartmentService {

    void createDepartment(Department department) throws ServiceException;

    Department getDepartmentById(int departmentId) throws ServiceException;

    void updateDepartment(Department department) throws ServiceException;

    void deleteDepartment(int departmentId) throws ServiceException;
}
