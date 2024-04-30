package service;

import exceptions.ServiceException;
import model.Instructor;

public interface InstructorService {

    void createInstructor(Instructor instructor) throws ServiceException;

    Instructor getInstructorById(int instructorId) throws ServiceException;

    void updateInstructor(Instructor instructor) throws ServiceException;

    void deleteInstructor(int instructorId) throws ServiceException;
}
