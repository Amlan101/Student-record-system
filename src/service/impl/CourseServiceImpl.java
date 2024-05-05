package service.impl;

import dao.CourseDAO;
import exceptions.ServiceException;
import model.Course;
import service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;

    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public boolean createCourse(Course course) throws ServiceException {
        try {
            courseDAO.insertCourse(course);
            return true;
        } catch (SQLException e) {
            throw new ServiceException("Error creating course: " + e.getMessage(), e);
        }
    }

    @Override
    public Course getCourseById(int courseId) throws ServiceException {
        try {
            return courseDAO.getCourseById(courseId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving course: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> getAllCourses() throws ServiceException {
        return courseDAO.getAllCourses();
    }

    @Override
    public boolean updateCourse(Course course) throws ServiceException {
        try {
            courseDAO.updateCourse(course);
        } catch (SQLException e) {
            throw new ServiceException("Error updating course: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void deleteCourse(int courseId) throws ServiceException {
        try {
            courseDAO.deleteCourse(courseId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting course: " + e.getMessage(), e);
        }
    }
}