/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManageStudentModel;

/**
 *
 * @author Amrin
 */
public class Registration {
    private Course course;
    private String semester;
    private String status; // Enrolled or Dropped

    public Registration(Course course, String semester, String status) {
        this.course = course;
        this.semester = semester;
        this.status = status;
}

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}