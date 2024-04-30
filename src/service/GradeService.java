package service;

import exceptions.ServiceException;
import model.Grade;

public interface GradeService {

    void createGrade(Grade grade) throws ServiceException;

    Grade getGradeById(int gradeId) throws ServiceException;

    void updateGrade(Grade grade) throws ServiceException;

    void deleteGrade(int gradeId) throws ServiceException;
}
