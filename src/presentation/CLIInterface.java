package presentation;

import dao.*;
import exceptions.ServiceException;
import model.*;
import service.*;
import service.impl.*;
import util.DatabaseConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import java.sql.Connection;
import java.sql.SQLException;


public class CLIInterface {

    private final StudentService studentService;
    private final CourseService courseService;
    private final DepartmentService departmentService;
    private final AssignmentService assignmentService;
    private final AttendanceService attendanceService;
    private final EnrollmentService enrollmentService;
    private final GradeService gradeService;
    private final InstructorService instructorService;


    public CLIInterface(StudentService studentService, CourseService courseService, DepartmentService departmentService, AssignmentService assignmentService, AttendanceService attendanceService, EnrollmentService enrollmentService, GradeService gradeService, InstructorService instructorService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.departmentService = departmentService;
        this.assignmentService = assignmentService;
        this.attendanceService = attendanceService;
        this.enrollmentService = enrollmentService;
        this.gradeService = gradeService;
        this.instructorService = instructorService;
    }

    public static void main(String[] args) {

        // Create instances of DAO implementations
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        GradeDAO gradeDAO = new GradeDAO();
        InstructorDAO instructorDAO = new InstructorDAO();

        // Create instances of service implementations
        StudentService studentService = new StudentServiceImpl(studentDAO);
        CourseService courseService = new CourseServiceImpl(courseDAO);
        DepartmentService departmentService = new DepartmentServiceImpl(departmentDAO);
        AssignmentService assignmentService = new AssignmentServiceImpl(assignmentDAO);
        AttendanceService attendanceService = new AttendanceServiceImpl(attendanceDAO);
        EnrollmentService enrollmentService = new EnrollmentServiceImpl(enrollmentDAO);
        GradeService gradeService = new GradeServiceImpl(gradeDAO);
        InstructorService instructorService = new InstructorServiceImpl(instructorDAO);



        CLIInterface cliInterface = new CLIInterface(studentService, courseService, departmentService, assignmentService, attendanceService, enrollmentService, gradeService, instructorService);
        cliInterface.start();
    }

    private static void testDatabaseConnection() {
        Connection connection = null;
        try {
            // Attempt to get a connection from the DatabaseConnection class
            connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }


    // Method to start the CLI interface
    public void start() {
        displayMainMenu(); // Display the main menu
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = getUserChoice(scanner); // Read user choice

            // Perform actions based on user choice
            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return; // Exit the program
                case 1:
                    manageStudentsMenu(scanner); // Call method to display students submenu
                    break;
                case 2:
                    manageCoursesMenu(scanner); // Call method to display courses submenu
                    break;
                case 3:
                    manageDepartmentsMenu(scanner); // Call method to display departments submenu -> Future
                    break;
                case 4:
                    manageInstructorsMenu(scanner); // Call method to display instructors submenu -> Future
                    break;
                case 5:
                    manageAssignmentsMenu(scanner); // Call method to display assignments submenu
                    break;
                case 6:
                    manageGradesMenu(scanner); // Call method to display grades submenu
                    break;
                case 7:
                    manageEnrollmentsMenu(scanner); // Call method to display enrollments submenu -> Future
                    break;
                case 8:
                    manageAttendanceMenu(scanner); // Call method to display attendance submenu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            displayMainMenu(); // Display the main menu again
        }
    }


    public void manageInstructorsMenu(Scanner scanner) {
        System.out.println("Work in progress...");
    }

    public void manageEnrollmentsMenu(Scanner scanner) {
        System.out.println("Work in progress...");
    }

    public void manageDepartmentsMenu(Scanner scanner) {
        System.out.println("Work in progress...");
    }

    public void manageAttendanceMenu(Scanner scanner) {
        while (true) {
            clearConsole(); // Clear the console
            System.out.println("\n** Manage Attendance **\n");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance Records");
            System.out.println("0. Back to main menu\n");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    return; // Return to the main menu
                case 1:
                    markAttendance(scanner); // Call method to mark attendance
                    break;
                case 2:
                    viewAttendanceRecords(); // Call method to view attendance records
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to mark the attendance
    public void markAttendance(Scanner scanner) {
        try {
            // Prompt the user to input attendance details
            System.out.println("\nMark Attendance:");
            System.out.print("Enter enrollment ID: ");
            int enrollmentId = scanner.nextInt();
            System.out.print("Enter attendance date (yyyy-MM-dd): ");
            String dateString = scanner.next();
            java.sql.Date attendanceDate = java.sql.Date.valueOf(dateString);
            System.out.print("Is the student present? (true/false): ");
            boolean present = scanner.nextBoolean();

            // Create a new Attendance object with the collected information
            Attendance attendance = new Attendance(enrollmentId, attendanceDate, present);

            // Call the appropriate method in AttendanceService to mark attendance
            boolean marked = attendanceService.createAttendance(attendance);
            if (marked) {
                System.out.println("Attendance marked successfully!");
            } else {
                System.out.println("Failed to mark attendance.");
            }
        } catch (ServiceException | IllegalArgumentException e) {
            System.out.println("Error marking attendance: " + e.getMessage());
        }
    }

    // Method to view attendance records
    public void viewAttendanceRecords() {
        try {
            // Retrieve all attendance records from the database
            List<Attendance> attendanceList = attendanceService.getAllAttendance();

            // Check if there are any attendance records
            if (attendanceList.isEmpty()) {
                System.out.println("No attendance records found.");
            } else {
                // Display each attendance record
                System.out.println("Attendance Records:");
                for (Attendance attendance : attendanceList) {
                    System.out.println("Attendance ID: " + attendance.getAttendanceId());
                    System.out.println("Enrollment ID: " + attendance.getEnrollmentId());
                    System.out.println("Attendance Date: " + attendance.getAttendanceDate());
                    System.out.println("Present: " + attendance.isPresent());
                    System.out.println("----------------------");
                }
            }
        } catch (ServiceException e) {
            System.out.println("Error retrieving attendance records: " + e.getMessage());
        }
    }

    public void manageGradesMenu(Scanner scanner) {
        while (true) {
            clearConsole(); // Clear the console
            System.out.println("\n** Manage Grades **\n");
            System.out.println("1. List all grades");
            System.out.println("2. View details of a specific grade");
            System.out.println("3. Create a new grade");
            System.out.println("4. Update an existing grade");
            System.out.println("5. Delete a grade");
            System.out.println("0. Back to main menu\n");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    return; // Return to the main menu
                case 1:
                    // Call method to list all grades
                    listGrades();
                    break;
                case 2:
                    // Call method to view details of a specific grade
                    System.out.print("Enter grade ID: ");
                    int gradeId = scanner.nextInt();
                    viewGradeDetails(gradeId);
                    break;
                case 3:
                    // Call method to create a new grade
                    createGrade(scanner);
                    break;
                case 4:
                    // Call method to update an existing grade
                    System.out.print("Enter grade ID to update: ");
                    gradeId = scanner.nextInt();
                    updateGrade(gradeId, scanner);
                    break;
                case 5:
                    // Call method to delete a grade
                    System.out.print("Enter grade ID to delete: ");
                    gradeId = scanner.nextInt();
                    deleteGrade(gradeId);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to list all grades
    public void listGrades() {
        try {
            // Call the appropriate method from your service layer to retrieve a list of all grades
            List<Grade> grades = gradeService.getAllGrades();

            // Check if the list of grades is empty
            if (grades.isEmpty()) {
                System.out.println("No grades found.");
                return;
            }

            // Print the header
            System.out.println("\nList of Grades:");

            // Iterate through the list and print out the details of each grade
            for (Grade grade : grades) {
                System.out.println("\nGrade ID: " + grade.getGradeId());
                System.out.println("Enrollment ID: " + grade.getEnrollmentId());
                System.out.println("Assignment ID: " + grade.getAssignmentId());
                System.out.println("CGPA: " + grade.getCgpa());
                System.out.println("Letter Grade: " + grade.getLetterGrade());
            }
        } catch (ServiceException e) {
            System.out.println("Error listing grades: " + e.getMessage());
        }
    }

    // Method to view a grade's details
    public void viewGradeDetails(int gradeId) {
        try {
            // Call the appropriate method from your service layer to retrieve the grade by ID
            Grade grade = gradeService.getGradeById(gradeId);

            // Check if the grade exists
            if (grade != null) {
                // Display the grade details
                System.out.println("\nGrade Details:");
                System.out.println("Grade ID: " + grade.getGradeId());
                System.out.println("Enrollment ID: " + grade.getEnrollmentId());
                System.out.println("Assignment ID: " + grade.getAssignmentId());
                System.out.println("CGPA: " + grade.getCgpa());
                System.out.println("Letter Grade: " + grade.getLetterGrade());
            } else {
                System.out.println("Grade with ID " + gradeId + " not found.");
            }
        } catch (ServiceException e) {
            System.out.println("Error viewing grade details: " + e.getMessage());
        }
    }

    // Method to create a new grade
    public void createGrade(Scanner scanner) {
        try {
            // Prompt the user to enter grade details
            System.out.println("\nCreate New Grade:");
            System.out.print("Enter enrollment ID: ");
            int enrollmentId = scanner.nextInt();
            System.out.print("Enter assignment ID: ");
            int assignmentId = scanner.nextInt();
            System.out.print("Enter CGPA: ");
            double cgpa = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter letter grade: ");
            String letterGrade = scanner.nextLine();

            // Create a new Grade object with the collected information
            Grade newGrade = new Grade();
            newGrade.setEnrollmentId(enrollmentId);
            newGrade.setAssignmentId(assignmentId);
            newGrade.setCgpa(cgpa);
            newGrade.setLetterGrade(letterGrade);

            // Call the appropriate method in GradeService to create the grade
            gradeService.createGrade(newGrade);
            System.out.println("Grade created successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Consume invalid input
        } catch (ServiceException e) {
            System.out.println("Error creating grade: " + e.getMessage());
        }
    }

    // Method to update a grade
    public void updateGrade(int gradeId, Scanner scanner) {
        try {
            // Get the existing grade details
            Grade existingGrade = gradeService.getGradeById(gradeId);

            // Display the existing grade details
            System.out.println("Current Grade Details:");
            System.out.println("Enrollment ID: " + existingGrade.getEnrollmentId());
            System.out.println("Assignment ID: " + existingGrade.getAssignmentId());
            System.out.println("CGPA: " + existingGrade.getCgpa());
            System.out.println("Letter Grade: " + existingGrade.getLetterGrade());

            // Prompt the user to enter the updated details
            System.out.println("\nEnter Updated Grade Details:");
            System.out.print("Enter new CGPA: ");
            double newCgpa = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Letter Grade: ");
            String newLetterGrade = scanner.nextLine();

            // Create a Grade object with the updated information
            Grade updatedGrade = new Grade();
            updatedGrade.setGradeId(gradeId); // Set the grade ID
            updatedGrade.setEnrollmentId(existingGrade.getEnrollmentId());
            updatedGrade.setAssignmentId(existingGrade.getAssignmentId());
            updatedGrade.setCgpa(newCgpa);
            updatedGrade.setLetterGrade(newLetterGrade);

            // Call the appropriate method from the GradeService to update the grade
            gradeService.updateGrade(updatedGrade);

            System.out.println("Grade updated successfully!");
        } catch (ServiceException e) {
            System.out.println("Error updating grade: " + e.getMessage());
        }
    }

    // Method to delete a grade
    public void deleteGrade(int gradeId) {
        try {
            // Call the appropriate method from the GradeService to delete the grade
            gradeService.deleteGrade(gradeId);
            System.out.println("Grade deleted successfully!");
        } catch (ServiceException e) {
            System.out.println("Error deleting grade: " + e.getMessage());
        }
    }



    public void manageAssignmentsMenu(Scanner scanner) {
        while (true) {
            clearConsole(); // Clear the console
            System.out.println("\n** Manage Assignments **\n");
            System.out.println("1. List all assignments");
            System.out.println("2. View details of a specific assignment");
            System.out.println("3. Create a new assignment");
            System.out.println("4. Update an existing assignment");
            System.out.println("5. Delete an assignment");
            System.out.println("0. Back to main menu\n");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    return; // Return to the main menu
                case 1:
                    // Call method to list all assignments
                    listAssignments();
                    break;
                case 2:
                    // Call method to view details of a specific assignment
                    System.out.print("Enter assignment ID: ");
                    int assignmentId = scanner.nextInt();
                    viewAssignmentDetails(assignmentId);
                    break;
                case 3:
                    // Call method to create a new assignment
                    createAssignment(scanner);
                    break;
                case 4:
                    // Call method to update an existing assignment
                    System.out.print("Enter assignment ID to update: ");
                    assignmentId = scanner.nextInt();
                    updateAssignment(assignmentId, scanner);
                    break;
                case 5:
                    // Call method to delete an assignment
                    System.out.print("Enter assignment ID to delete: ");
                    assignmentId = scanner.nextInt();
                    deleteAssignment(assignmentId);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to list all assignments
    public void listAssignments() {
        try {
            // Call the appropriate method from your service layer to retrieve a list of all assignments
            List<Assignment> assignments = assignmentService.getAllAssignments();

            // Check if the list is empty
            if (assignments.isEmpty()) {
                System.out.println("No assignments found.");
            } else {
                // Iterate through the list and print out the details of each assignment
                System.out.println("\nList of Assignments:");
                for (Assignment assignment : assignments) {
                    System.out.println("Assignment ID: " + assignment.getAssignmentId());
                    System.out.println("Title: " + assignment.getAssignmentName());
                    System.out.println("Description: " + assignment.getDescription());
                    System.out.println("Due Date: " + assignment.getDueDate());
                    System.out.println("-----------------------------");
                }
            }
        } catch (ServiceException e) {
            System.out.println("Error retrieving assignments: " + e.getMessage());
        }
    }

    // Method to find an assignment by its id
    public void viewAssignmentDetails(int assignmentId) {
        try {
            // Call the appropriate method from your service layer to retrieve the details of the assignment with the given ID
            Assignment assignment = assignmentService.getAssignmentById(assignmentId);

            // Check if the assignment exists
            if (assignment != null) {
                // Print out the details of the assignment
                System.out.println("Assignment ID: " + assignment.getAssignmentId());
                System.out.println("Title: " + assignment.getAssignmentName());
                System.out.println("Description: " + assignment.getDescription());
                System.out.println("Due Date: " + assignment.getDueDate());
                // Print other details of the assignment as needed
            } else {
                System.out.println("Assignment with ID " + assignmentId + " not found.");
            }
        } catch (ServiceException e) {
            System.out.println("Error retrieving assignment details: " + e.getMessage());
        }
    }

    // Method to create a new Assignment
    public void createAssignment(Scanner scanner) {
        try {
            // Prompt the user to enter assignment details
            System.out.println("\nCreate New Assignment:");
            System.out.print("Enter assignment title: ");
            String title = scanner.nextLine();
            System.out.print("Enter assignment description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter assignment due date (format: yyyy-mm-dd): ");
            String dueDateStr = scanner.nextLine();

            // Parse the due date string to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = dateFormat.parse(dueDateStr);

            // Create a new Assignment object with the collected information
            Assignment newAssignment = new Assignment();
            newAssignment.setAssignmentName(title);
            newAssignment.setDescription(desc);
            newAssignment.setDueDate(dueDate);

            // Call the appropriate method in AssignmentService to create the assignment
            assignmentService.createAssignment(newAssignment);
            System.out.println("Assignment created successfully!");
        } catch (ServiceException | ParseException e) {
            System.out.println("Error creating assignment: " + e.getMessage());
        }
    }

    // Method to update an assignment
    public void updateAssignment(int assignmentId, Scanner scanner) {
        try {
            // Prompt the user to enter updated assignment details
            System.out.println("\nUpdate Assignment:");
            System.out.print("Enter new assignment title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new assignment description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter new assignment due date (format: yyyy-MM-dd): ");
            String dueDateStr = scanner.nextLine();

            // Parse the due date string to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = dateFormat.parse(dueDateStr);

            // Create a new Assignment object with the updated information
            Assignment updatedAssignment = new Assignment();
            updatedAssignment.setAssignmentId(assignmentId); // Set the assignment ID
            updatedAssignment.setAssignmentName(title);
            updatedAssignment.setDescription(desc);
            updatedAssignment.setDueDate(dueDate);

            // Call the appropriate method in AssignmentService to update the assignment
            assignmentService.updateAssignment(updatedAssignment);
            System.out.println("Assignment updated successfully!");
        } catch (ParseException | ServiceException e) {
            System.out.println("Error updating assignment: " + e.getMessage());
        }
    }

    // Method to delete an assignment
    public void deleteAssignment(int assignmentId) {
        try {
            // Call the appropriate method in AssignmentService to delete the assignment
            assignmentService.deleteAssignment(assignmentId);
            System.out.println("Assignment deleted successfully!");
        } catch (ServiceException e) {
            System.out.println("Error deleting assignment: " + e.getMessage());
        }
    }

    public void manageCoursesMenu(Scanner scanner) {
        while (true) {
            clearConsole(); // Clear the console
            System.out.println("\n** Manage Courses **\n");
            System.out.println("1. List all courses");
            System.out.println("2. View details of a specific course");
            System.out.println("3. Create a new course");
            System.out.println("4. Update an existing course");
            System.out.println("5. Delete a course");
            System.out.println("0. Back to main menu\n");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    return; // Return to the main menu
                case 1:
                    // Call method to list all courses
                    listCourses();
                    break;
                case 2:
                    // Call method to view details of a specific course
                    System.out.print("Enter course ID: ");
                    int courseId = scanner.nextInt();
                    viewCourseDetails(courseId);
                    break;
                case 3:
                    // Call method to create a new course
                    createCourse(scanner);
                    break;
                case 4:
                    // Call method to update an existing course
                    System.out.print("Enter course ID to update: ");
                    courseId = scanner.nextInt();
                    updateCourse(courseId, scanner);
                    break;
                case 5:
                    // Call method to delete a course
                    System.out.print("Enter course ID to delete: ");
                    courseId = scanner.nextInt();
                    deleteCourse(courseId);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to list all courses
    public void listCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            System.out.println("\nList of Courses:\n");
            for (Course course : courses) {
                System.out.println(course); // Assuming you have implemented toString() method in Course class
            }
        } catch (ServiceException e) {
            System.out.println("Error listing courses: " + e.getMessage());
        }
    }

    // Method to view details of a specific course
    public void viewCourseDetails(int courseId) {
        try {
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                System.out.println("\nCourse Details:\n");
                System.out.println(course); // Assuming you have implemented toString() method in Course class
            } else {
                System.out.println("Course not found with ID: " + courseId);
            }
        } catch (ServiceException e) {
            System.out.println("Error viewing course details: " + e.getMessage());
        }
    }

    // Method to delete a course
    public void deleteCourse(int courseId) {
        try {
            // Call the appropriate method in CourseService to delete the course
            courseService.deleteCourse(courseId);
            System.out.println("Course deleted successfully!");
        } catch (ServiceException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }

    // Method to create a new course
    public void createCourse(Scanner scanner) {
        try {
            // Prompt the user for course details (name, description, etc.)
            System.out.print("Enter course name: ");
            String name = scanner.nextLine();
            System.out.print("Enter course description: ");
            String description = scanner.nextLine();
            // ... (collect other course information)

            // Create a Course object with the collected information
            Course course = new Course(name, description);

            // Call the createCourse method from your CourseService class
            // This method should create the course and return a success/failure indicator
            boolean created = courseService.createCourse(course);

            if (created) {
                System.out.println("Course created successfully!");
            } else {
                System.out.println("Failed to create course.");
            }
        } catch (ServiceException e) {
            System.out.println("Failed to create course: " + e.getMessage());
        }
    }

    // Method to update a course
    public void updateCourse(int courseId, Scanner scanner) {
        try {
            // Prompt the user for updated course details
            System.out.println("\nUpdate Course:");
            System.out.print("Enter new course name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new course description: ");
            String description = scanner.nextLine();

            // Create a Course object with the updated information
            Course updatedCourse = new Course(name, description);
            updatedCourse.setCourseId(courseId);

            // Call the updateCourse method from your CourseService class
            boolean updated = courseService.updateCourse(updatedCourse);

            if (updated) {
                System.out.println("Course updated successfully!");
            } else {
                System.out.println("Failed to update course.");
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            System.out.println("Failed to update course: " + e.getMessage());
        }
    }

    public void manageStudentsMenu(Scanner scanner) {
        while (true) {
            clearConsole(); // Clear the console
            System.out.println("\n** Manage Students **\n");
            System.out.println("1. List all students");
            System.out.println("2. View details of a specific student");
            System.out.println("3. Create a new student");
            System.out.println("4. Update an existing student");
            System.out.println("5. Delete a student");
            System.out.println("0. Back to main menu\n");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    return; // Return to the main menu
                case 1:
                    // Call method to list all students
                    listStudents();
                    break;
                case 2:
                    // Call method to view details of a specific student
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    viewStudentDetails(studentId);
                    break;
                case 3:
                    // Call method to create a new student
                    createStudent(scanner);
                    break;
                case 4:
                    // Call method to update an existing student
                    System.out.print("Enter student ID to update: ");
                    studentId = scanner.nextInt();
                    updateStudent(studentId, scanner);
                    break;
                case 5:
                    // Call method to delete a student
                    System.out.print("Enter student ID to delete: ");
                    studentId = scanner.nextInt();
                    deleteStudent(studentId);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    // Method to list all students
    public void listStudents() {
        try {
            // Call the appropriate method from your service layer to retrieve a list of all students
            List<Student> students = studentService.getAllStudents();

            if (students.isEmpty()) {
                System.out.println("No students found.");
            } else {
                System.out.println("List of Students:");
                for (Student student : students) {
                    System.out.println("ID: " + student.getStudentId());
                    System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("Email: " + student.getEmail());
                    System.out.println("--------------------");
                }
            }
        } catch (ServiceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to view details of a specific student
    public void viewStudentDetails(int studentId) {
        try {
            // Call the appropriate method from your service layer to retrieve the details of the student with the given ID
            Student student = studentService.getStudentById(studentId);

            if (student != null) {
                // Print out the details of the student
                System.out.println("Student Details:");
                System.out.println("ID: " + student.getStudentId());
                System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
                System.out.println("Email: " + student.getEmail());
                // You can print more details if needed
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } catch (ServiceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to create a new student
    public void createStudent(Scanner scanner) {
        try {
            System.out.println("Enter details of the new student:");

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Create a new Student object with the provided details
            Student newStudent = new Student();
            newStudent.setFirstName(firstName);
            newStudent.setLastName(lastName);
            newStudent.setEmail(email);

            // Call the appropriate method from your service layer to create the new student
            studentService.createStudent(newStudent);

            System.out.println("New student created successfully.");
        } catch (ServiceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to update an existing student
    public void updateStudent(int studentId, Scanner scanner) {
        try {
            System.out.println("Enter updated details of the student:");

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Create a new Student object with the updated details
            Student updatedStudent = new Student();
            updatedStudent.setStudentId(studentId); // Set the ID of the student to be updated
            updatedStudent.setFirstName(firstName);
            updatedStudent.setLastName(lastName);
            updatedStudent.setEmail(email);

            // Call the appropriate method from your service layer to update the student
            studentService.updateStudent(updatedStudent);

            System.out.println("Student updated successfully.");
        } catch (ServiceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete a student
    public void deleteStudent(int studentId) {
        try {
            // Call the appropriate method from your service layer to delete the student with the given ID
            studentService.deleteStudent(studentId);

            System.out.println("Student deleted successfully.");
        } catch (ServiceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void displayMainMenu() {
        clearConsole();
        System.out.println("\n\n** Student Record System **\n");
        // Print menu options
        System.out.println("Main Menu:");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Departments");
        System.out.println("4. Manage Instructors");
        System.out.println("5. Manage Assignments");
        System.out.println("6. Manage Grades");
        System.out.println("7. Manage Enrollments");
        System.out.println("8. Manage Attendance");
        System.out.println("0. Exit");

        System.out.print("Please enter your choice: ");

    }

    // Method to clear the console
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Handle exceptions
        }
    }

    // Method to read user input
    public int getUserChoice(Scanner scanner) {
        return scanner.nextInt();
    }

}