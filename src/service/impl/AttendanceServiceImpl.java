package service.impl;

import dao.AttendanceDAO;
import exceptions.ServiceException;
import model.Attendance;
import service.AttendanceService;

import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDAO attendanceDAO;

    public AttendanceServiceImpl(AttendanceDAO attendanceDAO) {
        this.attendanceDAO = attendanceDAO;
    }

    @Override
    public boolean createAttendance(Attendance attendance)  {
        return attendanceDAO.insertAttendance(attendance);
    }

    @Override
    public Attendance getAttendanceById(int attendanceId) {
        return attendanceDAO.getAttendanceById(attendanceId);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceDAO.getAllAttendance();
    }

    @Override
    public boolean updateAttendance(Attendance attendance) {
        return attendanceDAO.updateAttendance(attendance);
    }

    @Override
    public boolean deleteAttendance(int attendanceId)  {
        return attendanceDAO.deleteAttendance(attendanceId);
    }
}