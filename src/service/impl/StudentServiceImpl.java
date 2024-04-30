package service.impl;

import dao.StudentDAO;
import exceptions.ServiceException;
import model.Student;
import service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student createStudent(Student student) throws ServiceException {
        try {
            return studentDAO.insertStudent(student);
        } catch (SQLException e) {
            throw new ServiceException("Error creating student: " + e.getMessage(), e);
        }
    }

    @Override
    public Student getStudentById(int studentId) throws ServiceException {
        try {
            return studentDAO.getStudentById(studentId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving student: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Student> getAllStudents() throws ServiceException {
        try {
            return studentDAO.getAllStudents();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all students: " + e.getMessage(), e);
        }
    }

    @Override
    public Student updateStudent(Student student) throws ServiceException {
        try {
            return studentDAO.updateStudent(student);
        } catch (SQLException e) {
            throw new ServiceException("Error updating student: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudent(int studentId) throws ServiceException {
        try {
            studentDAO.deleteStudent(studentId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting student: " + e.getMessage(), e);
        }
    }
}
