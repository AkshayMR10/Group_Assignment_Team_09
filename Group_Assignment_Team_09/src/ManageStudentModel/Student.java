package ManageStudentModel;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Amrin
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String program;
    private double balance;

    // New fields for graduation audit
    private List<Course> enrolledCourses;

    public Student(String studentId, String name, String email, String phone, String address, String program, double balance) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.program = program;
        this.balance = balance;
        this.enrolledCourses = new ArrayList<>();
    }

    // Existing getters and setters...
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    // New methods for graduation audit
    public void addCourse(Course course) {
        enrolledCourses.add(course);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public int getTotalCredits() {
        return enrolledCourses.stream().mapToInt(Course::getCredits).sum();
    }

    public boolean hasCoreCourse() {
        return enrolledCourses.stream().anyMatch(Course::isCore);
    }

    public boolean isEligibleToGraduate() {
        return hasCoreCourse() && getTotalCredits() >= 32;
    }
}
