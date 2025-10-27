/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.CourseSchedule;

import Business.CourseCatalog.Course;

/**
 * @author Sriya
 */
public class SeatAssignment {

    public float grade;  // e.g., A=4.0, A-=3.7, B+=3.3, etc.
    Seat seat;
    boolean like; // true = liked course, false = disliked
    CourseLoad courseload;

    // -------------------- Constructor --------------------
    public SeatAssignment(CourseLoad cl, Seat s) {
        this.seat = s;
        this.courseload = cl;
        this.grade = 0f;
    }

    // -------------------- Getters & Setters --------------------
    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getCreditHours() {
        return seat.getCourseCredits();
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Seat getSeat() {
        return seat;
    }

    public CourseLoad getCourseLoad() {
        return courseload;
    }

    public CourseLoad getCourseload() {
        return courseload;
    }

    public void assignSeatToStudent(CourseLoad cl) {
        this.courseload = cl;
    }

    // -------------------- Course Related Info --------------------
    /**
     * Get the CourseOffer associated with this seat.
     */
    public CourseOffer getCourseOffer() {
        return seat.getCourseOffer();
    }

    /**
     * Get the actual Course object from this seat assignment.
     */
    public Course getAssociatedCourse() {
        return getCourseOffer().getSubjectCourse();
    }

    /**
     * Get the Course ID for convenience.
     */
    public String getCourseId() {
        return getAssociatedCourse().getCourseId();
    }

    /**
     * Get the Course Name for convenience.
     */
    public String getCourseName() {
        return getAssociatedCourse().getCourseName();
    }

    /**
     * Calculate weighted course score (grade * credits).
     */
    public float getCourseStudentScore() {
        return getCreditHours() * grade;
    }

    // -------------------- Utility --------------------
    @Override
    public String toString() {
        return getCourseId() + " - " + getCourseName();
    }

    float GetCourseStudentScore() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
