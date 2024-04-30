package service;

import exceptions.ServiceException;
import model.Attendance;
import java.util.List;

public interface AttendanceService {

    boolean createAttendance(Attendance attendance) throws ServiceException;

    Attendance getAttendanceById(int attendanceId) throws ServiceException;

    List<Attendance> getAllAttendance() throws ServiceException;

    boolean updateAttendance(Attendance attendance) throws ServiceException;

    boolean deleteAttendance(int attendanceId) throws ServiceException;
}
