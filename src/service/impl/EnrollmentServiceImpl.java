package service.impl;

import dao.EnrollmentDAO;
import exceptions.ServiceException;
import model.Enrollment;
import service.EnrollmentService;

import java.sql.SQLException;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentServiceImpl(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    @Override
    public void createEnrollment(Enrollment enrollment) throws ServiceException {
        try {
            enrollmentDAO.insertEnrollment(enrollment);
        } catch (SQLException e) {
            throw new ServiceException("Error creating enrollment: " + e.getMessage(), e);
        }
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) throws ServiceException {
        try {
            return enrollmentDAO.getEnrollmentById(enrollmentId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving enrollment: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) throws ServiceException {
        try {
            enrollmentDAO.updateEnrollment(enrollment);
        } catch (SQLException e) {
            throw new ServiceException("Error updating enrollment: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteEnrollment(int enrollmentId) throws ServiceException {
        try {
            enrollmentDAO.deleteEnrollment(enrollmentId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting enrollment: " + e.getMessage(), e);
        }
    }
}