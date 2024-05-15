package model;

public class Course {
    private int courseId;
    private String courseName;
    private String courseDescription;

    // Constructors
    public Course() {
    }

    public Course(String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId +
                "\nCourse Name: " + courseName +
                "\nCourse Description: " + courseDescription + "\n";
    }

}
