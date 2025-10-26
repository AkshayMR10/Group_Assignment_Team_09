/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManageStudentModel;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Amrin
 */
public class Course {
    private String courseId;
    private String courseName;
    private String assignmentTitle;
    private Date dueDate;
    private String submissionStatus; // e.g., "Submitted", "Pending"
    private double grade; // 0 if not graded yet
    private String feedback;
    private boolean isCore;
    private int credits;

    public Course(String courseId, String courseName, String assignmentTitle, Date dueDate, boolean isCore, int credits) {
        this.courseName = courseName;
        this.assignmentTitle = assignmentTitle;
        this.dueDate = dueDate;
        this.submissionStatus = "Pending";
        this.grade = 0.0;
        this.isCore = isCore;
        this.credits = credits;
        this.feedback = "";
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public boolean isCore() { return isCore; }
    public int getCredits() { return credits; }
    public String getCourseId() { return courseId; }
}