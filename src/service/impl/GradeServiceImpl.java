package service.impl;

import dao.GradeDAO;
import exceptions.ServiceException;
import model.Grade;
import service.GradeService;

import java.sql.SQLException;

public class GradeServiceImpl implements GradeService {

    private final GradeDAO gradeDAO;

    public GradeServiceImpl(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

    @Override
    public void createGrade(Grade grade) throws ServiceException {
        try {
            gradeDAO.insertGrade(grade);
        } catch (SQLException e) {
            throw new ServiceException("Error creating grade: " + e.getMessage(), e);
        }
    }

    @Override
    public Grade getGradeById(int gradeId) throws ServiceException {
        try {
            return gradeDAO.getGradeById(gradeId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving grade: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateGrade(Grade grade) throws ServiceException {
        try {
            gradeDAO.updateGrade(grade);
        } catch (SQLException e) {
            throw new ServiceException("Error updating grade: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteGrade(int gradeId) throws ServiceException {
        try {
            gradeDAO.deleteGrade(gradeId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting grade: " + e.getMessage(), e);
        }
    }
}
