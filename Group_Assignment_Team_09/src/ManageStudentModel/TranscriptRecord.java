/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManageStudentModel;

/**
 *
 * @author Amrin
 */
public class TranscriptRecord {
    private String term;             // e.g., "Fall 2025"
    private String courseId;         // e.g., "INFO 5100"
    private String courseName;       // e.g., "Application Engineering and Development"
    private String grade;            // e.g., "A", "B+", "C"
    private double creditHours;      // e.g., 3.0
    private double termGpaPoints;    // GPA for that term
    private double overallGpaPoints; // Overall GPA till now
    private String academicStanding; // Good Standing / Warning / Probation

    // Constructor
    public TranscriptRecord(String term, String courseId, String courseName, String grade, double creditHours) {
        this.term = term;
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
        this.creditHours = creditHours;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(double creditHours) {
        this.creditHours = creditHours;
    }

    public double getTermGpaPoints() {
        return termGpaPoints;
    }

    public void setTermGpaPoints(double termGpaPoints) {
        this.termGpaPoints = termGpaPoints;
    }

    public double getOverallGpaPoints() {
        return overallGpaPoints;
    }

    public void setOverallGpaPoints(double overallGpaPoints) {
        this.overallGpaPoints = overallGpaPoints;
    }

    public String getAcademicStanding() {
        return academicStanding;
    }

    public void setAcademicStanding(String academicStanding) {
        this.academicStanding = academicStanding;
    }
}
