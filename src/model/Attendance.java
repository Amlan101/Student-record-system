package model;

import java.util.Date;

public class Attendance {
    private int attendanceId;
    private int enrollmentId;
    private Date attendanceDate;
    private boolean present;

    // Constructors
    public Attendance() {
    }

    public Attendance(int enrollmentId, Date attendanceDate, boolean present) {
        this.enrollmentId = enrollmentId;
        this.attendanceDate = attendanceDate;
        this.present = present;
    }

    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
