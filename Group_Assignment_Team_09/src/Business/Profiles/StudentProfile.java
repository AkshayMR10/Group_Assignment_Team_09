/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Profiles;

import Business.Person.Person;
import Business.CourseSchedule.CourseLoad;
import Business.CourseSchedule.SeatAssignment;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */
public class StudentProfile extends Profile {

    Person person;

    // Student-specific fields
    private String department;
    private String program;  // e.g., "MSIS"
    private String enrollmentDate;
    private String academicStatus;  // Good Standing, Warning, Probation

    // Transcript (Course History)
    private HashMap<String, CourseLoad> courseloadlist;
    private CourseLoad currentcourseload;

    public StudentProfile(Person p) {
        super(p);
        this.person = p;
        this.academicStatus = "Good Standing";  // Default
        this.courseloadlist = new HashMap();
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }

    // Getters and Setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    // Course Load Methods (from University Model)
    public CourseLoad newCourseLoad(String semester) {
        currentcourseload = new CourseLoad(semester);
        courseloadlist.put(semester, currentcourseload);
        return currentcourseload;
    }

    public CourseLoad getCurrentCourseLoad() {
        return currentcourseload;
    }

    public CourseLoad getCourseLoadBySemester(String semester) {
        return courseloadlist.get(semester);
    }

    public ArrayList<SeatAssignment> getCourseList() {
        ArrayList<SeatAssignment> allCourses = new ArrayList();
        for (CourseLoad cl : courseloadlist.values()) {
            allCourses.addAll(cl.getSeatAssignments());
        }
        return allCourses;
    }

    public HashMap<String, CourseLoad> getCourseLoadList() {
        return courseloadlist;
    }

    @Override
    public String toString() {
        return getPerson().getName() + " (" + program + ")";
    }
}
