package model;

import java.util.Date;

public class Assignment {
    private int assignmentId;
    private int courseId;
    private String assignmentName;
    private Date dueDate;
    private String description;

    // Constructors
    public Assignment() {
    }

    public Assignment(int courseId, String assignmentName, Date dueDate, String description) {
        this.courseId = courseId;
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.description = description;
    }

    // Getters and Setters
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
