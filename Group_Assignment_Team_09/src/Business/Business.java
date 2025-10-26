/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.StudentDirectory;
import Business.Profiles.FacultyDirectory;
import Business.Profiles.RegistrarDirectory;
import Business.UserAccounts.UserAccountDirectory;
import Business.CourseCatalog.CourseCatalog;
import Business.CourseSchedule.CourseOffer;
import Business.CourseSchedule.CourseSchedule;
import Business.Degree.Degree;
import Business.Profiles.FacultyProfile;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory; //all people profiles regardless of the role

    EmployeeDirectory employeedirectory;
    UserAccountDirectory useraccountdirectory;
    StudentDirectory studentdirectory;
    FacultyDirectory facultydirectory;        // NEW
    RegistrarDirectory registrardirectory;    // NEW

    // Course Management (from University Model)
    CourseCatalog coursecatalog;
    HashMap<String, CourseSchedule> mastercoursecatalog;
    Degree degree;

    public Business(String n) {
        name = n;

        persondirectory = new PersonDirectory();
        employeedirectory = new EmployeeDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        studentdirectory = new StudentDirectory();
        facultydirectory = new FacultyDirectory();      // NEW
        registrardirectory = new RegistrarDirectory();  // NEW

        // Initialize course structures
        coursecatalog = new CourseCatalog(this);
        mastercoursecatalog = new HashMap<>();
        degree = new Degree("MSIS");
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public StudentDirectory getStudentDirectory() {
        return studentdirectory;
    }

    // NEW getters
    public FacultyDirectory getFacultyDirectory() {
        return facultydirectory;
    }

    public RegistrarDirectory getRegistrarDirectory() {
        return registrardirectory;
    }

    // Course Management Methods
    public CourseCatalog getCourseCatalog() {
        return coursecatalog;
    }

    public Degree getDegree() {
        return degree;
    }

    public HashMap<String, CourseSchedule> getMasterCourseCatalog() {
        return mastercoursecatalog;
    }

    /**
     * Create a new course schedule for a semester
     */
    public CourseSchedule newCourseSchedule(String semester) {
        CourseSchedule cs = new CourseSchedule(semester, coursecatalog);
        mastercoursecatalog.put(semester, cs);
        return cs;
    }

    /**
     * Get course schedule for a specific semester
     */
    public CourseSchedule getCourseSchedule(String semester) {
        return mastercoursecatalog.get(semester);
    }

    /**
     * Calculate total tuition revenue for a semester
     */
    public int calculateRevenuesBySemester(String semester) {
        CourseSchedule cs = mastercoursecatalog.get(semester);
        if (cs == null) {
            return 0;
        }
        return cs.calculateTotalRevenues();
    }

    /**
     * Get total tuition revenue across all semesters
     */
    public int calculateTotalRevenues() {
        int total = 0;
        for (CourseSchedule cs : mastercoursecatalog.values()) {
            total += cs.calculateTotalRevenues();
        }
        return total;
    }

    //Get all course offers assigned to a specific faculty member
    public ArrayList<CourseOffer> getCourseOffersForFaculty(FacultyProfile facultyProfile) {
        ArrayList<CourseOffer> assignedCourses = new ArrayList<>();

        if (facultyProfile == null) {
            return assignedCourses;
        }

        // Iterate through all semesters
        for (CourseSchedule cs : mastercoursecatalog.values()) {
            // Iterate through all course offers
            for (CourseOffer co : cs.getSchedule()) {
                // Check if this course is assigned to the faculty
                FacultyProfile assignedFaculty = co.getFacultyProfile();
                if (assignedFaculty != null && assignedFaculty.equals(facultyProfile)) {
                    assignedCourses.add(co);
                }
            }
        }

        return assignedCourses;
    }

    //Get formatted string of courses for a faculty member
    public String getCoursesStringForFaculty(FacultyProfile facultyProfile) {
        ArrayList<CourseOffer> courses = getCourseOffersForFaculty(facultyProfile);

        if (courses.isEmpty()) {
            return "Not Assigned";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            sb.append(courses.get(i).getCourseNumber());
            if (i < courses.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

}
