package service.impl;


import dao.AssignmentDAO;
import exceptions.ServiceException;
import model.Assignment;
import service.AssignmentService;

import java.sql.SQLException;
import java.util.List;

public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentDAO assignmentDAO;

    public AssignmentServiceImpl(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    @Override
    public Assignment createAssignment(Assignment assignment) throws ServiceException {
        try {
            assignmentDAO.insertAssignment(assignment);
            return assignment;
        } catch (SQLException e) {
            throw new ServiceException("Error creating assignment", e);
        }
    }

    @Override
    public Assignment getAssignmentById(int assignmentId) throws ServiceException {
        try {
            return assignmentDAO.getAssignmentById(assignmentId);
        } catch (SQLException e) {
            throw new ServiceException("Error getting assignment by ID", e);
        }
    }

    @Override
    public List<Assignment> getAllAssignments() throws ServiceException {
        try {
            return assignmentDAO.getAllAssignments();
        } catch (SQLException e) {
            throw new ServiceException("Error getting all assignments", e);
        }
    }

    @Override
    public Assignment updateAssignment(Assignment assignment) throws ServiceException {
        try {
            assignmentDAO.updateAssignment(assignment);
            return assignment;
        } catch (SQLException e) {
            throw new ServiceException("Error updating assignment", e);
        }
    }

    @Override
    public void deleteAssignment(int assignmentId) throws ServiceException {
        try {
            assignmentDAO.deleteAssignment(assignmentId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting assignment", e);
        }
    }
}
