package Business.Profiles;

import Business.Person.Person;
import Business.CourseSchedule.CourseLoad;
import Business.CourseSchedule.SeatAssignment;
import ManageStudentModel.Course;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StudentProfile represents a student in the system, including their
 * personal information, course history, financials, and graduation tracking.
 */
public class StudentProfile extends Profile {

    // -------------------- Core Fields --------------------
    private Person person;
    private String department;
    private String program;            // e.g., "MSIS"
    private String enrollmentDate;
    private String academicStatus;     // e.g., Good Standing, Warning, Probation

    // Transcript / Course History
    private HashMap<String, CourseLoad> courseloadlist;
    private CourseLoad currentcourseload;

    // -------------------- Graduation & Finance Fields --------------------
    private List<Course> enrolledCourses;       // Courses added for graduation
    private double balance;                      // Total balance due
    private Map<String, Double> courseFees;     // courseId -> fee
    private List<String> paymentHistory;        // Track all billing/payment/refund history

    // -------------------- Constructor --------------------
    public StudentProfile(Person p) {
        super(p);
        this.person = p;
        this.academicStatus = "Good Standing";
        this.courseloadlist = new HashMap<>();
        this.enrolledCourses = new ArrayList<>();
        this.balance = 0.0;
        this.courseFees = new HashMap<>();
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

    public Person getPerson() { return person; }

    // -------------------- Course Load Methods --------------------
    public CourseLoad newCourseLoad(String semester) {
        currentcourseload = new CourseLoad(semester);
        courseloadlist.put(semester, currentcourseload);
        return currentcourseload;
    }

    public CourseLoad getCurrentCourseLoad() { return currentcourseload; }

    public CourseLoad getCourseLoadBySemester(String semester) { 
        return courseloadlist.get(semester); 
    }

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
        addCourseFee(course.getCourseId(), course.getCredits() * 1000); // $1000 per credit
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
    /** Add course-specific fee */
    public void addCourseFee(String courseId, double fee) {
        if (courseFees == null) courseFees = new HashMap<>();
        courseFees.put(courseId, fee);
        balance += fee;
        paymentHistory.add("Billed $" + String.format("%.2f", fee) + " for course " + courseId + " on " + java.time.LocalDate.now());
    }

    /** Pay tuition */
    public void payTuition(double amount) {
        if (amount <= 0 || balance <= 0) return;
        balance -= amount;
        paymentHistory.add("Paid $" + String.format("%.2f", amount) + " on " + java.time.LocalDate.now());
    }

    /** Refund tuition (if course dropped) */
    public void refundTuition(String courseId) {
        double fee = courseFees.getOrDefault(courseId, 0.0);
        if (fee > 0) {
            balance -= fee;
            paymentHistory.add("Refunded $" + String.format("%.2f", fee) + " for course " + courseId + " on " + java.time.LocalDate.now());
            courseFees.remove(courseId);
        }
    }

    /** Get specific course fee */
    public double getCourseFee(String courseId) {
        return courseFees.getOrDefault(courseId, 0.0);
    }

    /** Get full payment history */
    public List<String> getPaymentHistory() {
        if (paymentHistory == null) paymentHistory = new ArrayList<>();
        return paymentHistory;
    }

    /** Get current balance */
    public double getBalance() {
        return balance;
    }

    // -------------------- ToString --------------------
    @Override
    public String toString() {
        return getPerson().getName() + " (" + program + ")";
    }
}
