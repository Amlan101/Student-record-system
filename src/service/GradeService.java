package service;

import exceptions.ServiceException;
import model.Grade;

import java.util.List;

public interface GradeService {

    void createGrade(Grade grade) throws ServiceException;

    Grade getGradeById(int gradeId) throws ServiceException;

    void updateGrade(Grade grade) throws ServiceException;

    void deleteGrade(int gradeId) throws ServiceException;

    List<Grade> getAllGrades() throws ServiceException;
}
