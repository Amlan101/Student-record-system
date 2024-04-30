package service.impl;


import dao.InstructorDAO;
import exceptions.ServiceException;
import model.Instructor;
import service.InstructorService;

import java.sql.SQLException;

public class InstructorServiceImpl implements InstructorService {

    private final InstructorDAO instructorDAO;

    public InstructorServiceImpl(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @Override
    public void createInstructor(Instructor instructor) throws ServiceException {
        try {
            instructorDAO.insertInstructor(instructor);
        } catch (SQLException e) {
            throw new ServiceException("Error creating instructor: " + e.getMessage(), e);
        }
    }

    @Override
    public Instructor getInstructorById(int instructorId) throws ServiceException {
        try {
            return instructorDAO.getInstructorById(instructorId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving instructor: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateInstructor(Instructor instructor) throws ServiceException {
        try {
            instructorDAO.updateInstructor(instructor);
        } catch (SQLException e) {
            throw new ServiceException("Error updating instructor: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteInstructor(int instructorId) throws ServiceException {
        try {
            instructorDAO.deleteInstructor(instructorId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting instructor: " + e.getMessage(), e);
        }
    }
}