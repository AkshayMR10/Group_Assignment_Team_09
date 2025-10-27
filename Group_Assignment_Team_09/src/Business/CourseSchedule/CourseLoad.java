/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.CourseSchedule;

/**
 *
 * @author aksha
 */
import Business.Profiles.StudentProfile;
import java.util.ArrayList;

public class CourseLoad {

    String semester;
    ArrayList<SeatAssignment> seatassignments;
    private StudentProfile studentProfile;

    public CourseLoad(String s) {
        seatassignments = new ArrayList();
        semester = s;
    }

     public CourseLoad(String s, StudentProfile sp) {
        seatassignments = new ArrayList();
        semester = s;
        studentProfile = sp;
    }
    public SeatAssignment newSeatAssignment(CourseOffer co) {
        Seat seat = co.getEmptySeat(); // seat linked to courseoffer
        if (seat == null) {
            return null;
        }
        SeatAssignment sa = seat.newSeatAssignment(this);
        seatassignments.add(sa);  //add to students course 
        return sa;
    }

    public void registerStudent(SeatAssignment sa) {
        sa.assignSeatToStudent(this);
        seatassignments.add(sa);
    }

    public float getSemesterScore() { //total score for a full semester
        float sum = 0;
        for (SeatAssignment sa : seatassignments) {
            sum = sum + sa.GetCourseStudentScore();
        }
        return sum;
    }

    public ArrayList<SeatAssignment> getSeatAssignments() {
        return seatassignments;
    }

    public String getSemester() {
        return semester;
    }
     public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    
    public void setStudentProfile(StudentProfile sp) {
        this.studentProfile = sp;
    }
}
