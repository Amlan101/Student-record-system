package service;

import exceptions.ServiceException;
import model.Enrollment;

public interface EnrollmentService {

    void createEnrollment(Enrollment enrollment) throws ServiceException;

    Enrollment getEnrollmentById(int enrollmentId) throws ServiceException;

    void updateEnrollment(Enrollment enrollment) throws ServiceException;

    void deleteEnrollment(int enrollmentId) throws ServiceException;
}
