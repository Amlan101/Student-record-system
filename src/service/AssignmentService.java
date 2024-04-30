package service;

import exceptions.ServiceException;
import model.Assignment;
import java.util.List;

public interface AssignmentService {

    Assignment createAssignment(Assignment assignment) throws ServiceException;

    Assignment getAssignmentById(int assignmentId) throws ServiceException;

    List<Assignment> getAllAssignments() throws ServiceException;

    Assignment updateAssignment(Assignment assignment) throws ServiceException;

    void deleteAssignment(int assignmentId) throws ServiceException;
}
