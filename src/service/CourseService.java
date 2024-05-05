package service;

import exceptions.ServiceException;
import model.Course;
import java.util.List;

public interface CourseService {

    boolean createCourse(Course course) throws ServiceException;

    Course getCourseById(int courseId) throws ServiceException;

    List<Course> getAllCourses() throws ServiceException;

    boolean updateCourse(Course course) throws ServiceException;

    void deleteCourse(int courseId) throws ServiceException;
}
