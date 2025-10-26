/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManageStudentModel;

/**
 *
 * @author Amrin
 */
public class CourseCatalog {
    private String courseId;
    private String courseName;
    private String instructor;
    private int creditHours;
    private String semester;

    public CourseCatalog(String courseId, String courseName, String instructor, int creditHours, String semester) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.creditHours = creditHours;
        this.semester = semester;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
        @Override
    public String toString() {
        return courseId + " - " + courseName;
    }
}