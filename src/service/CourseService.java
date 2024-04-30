package service;

import exceptions.ServiceException;
import model.Course;
import java.util.List;

public interface CourseService {

    void createCourse(Course course) throws ServiceException;

    Course getCourseById(int courseId) throws ServiceException;

    List<Course> getAllCourses() throws ServiceException;

    void updateCourse(Course course) throws ServiceException;

    void deleteCourse(int courseId) throws ServiceException;
}
