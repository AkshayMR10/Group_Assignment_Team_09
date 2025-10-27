/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Profiles;
/**
 *
 * @author Amrin
 */
import Business.Person.Person;
import Business.CourseSchedule.CourseLoad;
import Business.CourseSchedule.SeatAssignment;
import ManageStudentModel.Course;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentProfile extends Profile {

    // -------------------- Core Fields --------------------
    private Person person;

    private String department;
    private String program;  // e.g., "MSIS"
    private String enrollmentDate;
    private String academicStatus;  // Good Standing, Warning, Probation

    // Transcript / Course History
    private HashMap<String, CourseLoad> courseloadlist;
    private CourseLoad currentcourseload;
    // -------------------- Graduation & Finance Fields --------------------
    private List<Course> enrolledCourses;       // Courses added for graduation
    private double balance;                      // Tuition balance
    private List<String> paymentHistory;         // Track all billing/payment/refund history

    // -------------------- Constructor --------------------
    public StudentProfile(Person p) {
        super(p);
        this.person = p;
        this.academicStatus = "Good Standing";
        this.courseloadlist = new HashMap<>();
        this.enrolledCourses = new ArrayList<>();
        this.balance = 0.0;
        this.paymentHistory = new ArrayList<>();
    }

    // -------------------- Role --------------------
    @Override
    public String getRole() {
        return "Student";
    }

    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }

    // -------------------- Getters / Setters --------------------
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    public String getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(String enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    public String getAcademicStatus() { return academicStatus; }
    public void setAcademicStatus(String academicStatus) { this.academicStatus = academicStatus; }

    // -------------------- Course Load Methods --------------------
    public CourseLoad newCourseLoad(String semester) {
        currentcourseload = new CourseLoad(semester);
        courseloadlist.put(semester, currentcourseload);
        return currentcourseload;
    }

    public CourseLoad getCurrentCourseLoad() { return currentcourseload; }
    public CourseLoad getCourseLoadBySemester(String semester) { return courseloadlist.get(semester); }

    public ArrayList<SeatAssignment> getCourseList() {
        ArrayList<SeatAssignment> allCourses = new ArrayList<>();
        for (CourseLoad cl : courseloadlist.values()) {
            allCourses.addAll(cl.getSeatAssignments());
        }
        return allCourses;
    }

    public HashMap<String, CourseLoad> getCourseLoadList() { return courseloadlist; }

    // -------------------- Graduation Audit Methods --------------------
    public void addCourse(Course course) {
        enrolledCourses.add(course);
        billTuition(course); // Automatically bill tuition when course is added
    }

    public List<Course> getEnrolledCourses() { return enrolledCourses; }

    public int getTotalCredits() {
        return enrolledCourses.stream().mapToInt(Course::getCredits).sum();
    }

    public boolean hasCoreCourse() {
        return enrolledCourses.stream().anyMatch(Course::isCore);
    }

    public boolean isEligibleToGraduate() {
        return hasCoreCourse() && getTotalCredits() >= 32;
    }

    // -------------------- Financial Management Methods --------------------
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    /** Bill tuition for a course */
    public void billTuition(Course course) {
        double courseFee = course.getCredits() * 1000; // Example: $1000 per credit
        balance += courseFee;
        paymentHistory.add("Billed $" + courseFee + " for " + course.getCourseName());
    }

    /** Pay tuition */
    public boolean payTuition(double amount) {
        if (balance <= 0) return false; // Nothing to pay
        double payment = Math.min(amount, balance);
        balance -= payment;
        paymentHistory.add("Paid $" + payment);
        return true;
    }

    /** Refund tuition (if course dropped) */
    public void refundTuition(double amount, Course course) {
        balance -= amount;
        paymentHistory.add("Refunded $" + amount + " for " + course.getCourseName());
    }

    /** Get payment history */
    public List<String> getPaymentHistory() {
        return paymentHistory;
    }

    // -------------------- ToString --------------------
    @Override
    public String toString() {
        return getPerson().getName() + " (" + program + ")";
    }
}
