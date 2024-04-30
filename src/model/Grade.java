package model;

public class Grade {
    private int gradeId;
    private int enrollmentId;
    private int assignmentId;
    private double cgpa;
    private String letterGrade;

    // Constructors
    public Grade() {
    }

    public Grade(int enrollmentId, int assignmentId, double cgpa, String letterGrade) {
        this.enrollmentId = enrollmentId;
        this.assignmentId = assignmentId;
        this.cgpa = cgpa;
        this.letterGrade = letterGrade;
    }

    // Getters and Setters
    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
}
