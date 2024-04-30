package service;

import exceptions.ServiceException;
import model.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student) throws ServiceException;

    Student getStudentById(int studentId) throws ServiceException;

    List<Student> getAllStudents() throws ServiceException;

    Student updateStudent(Student student) throws ServiceException;

    void deleteStudent(int studentId) throws ServiceException;
}
