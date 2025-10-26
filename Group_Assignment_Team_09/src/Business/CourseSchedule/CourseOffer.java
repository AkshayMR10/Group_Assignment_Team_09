/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.CourseSchedule;

/**
 *
 * @author aksha
 */
import Business.CourseCatalog.Course;
import Business.Profiles.FacultyProfile;
import java.util.ArrayList;

public class CourseOffer {

    Course course;
    ArrayList<Seat> seatlist;
    FacultyProfile facultyProfile;  // Changed from FacultyAssignment to FacultyProfile

    public CourseOffer(Course c) {
        course = c;
        seatlist = new ArrayList();
    }

    public void AssignAsTeacher(FacultyProfile fp) {
        facultyProfile = fp;
    }

    public FacultyProfile getFacultyProfile() {
        return facultyProfile;
    }

    public String getCourseNumber() {
        return course.getCourseNumber();
    }

    public void generatSeats(int n) {
        for (int i = 0; i < n; i++) {
            seatlist.add(new Seat(this, i));
        }
    }

    public Seat getEmptySeat() {
        for (Seat s : seatlist) {
            if (!s.isOccupied()) {
                return s;
            }
        }
        return null;
    }

    public SeatAssignment assignEmptySeat(CourseLoad cl) {
        Seat seat = getEmptySeat();
        if (seat == null) {
            return null;
        }
        SeatAssignment sa = seat.newSeatAssignment(cl); //seat is already linked to course offer
        cl.registerStudent(sa); //course offer seat is now linked to student
        return sa;
    }

    public int getTotalCourseRevenues() {
        int sum = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied() == true) {
                sum = sum + course.getCoursePrice();
            }
        }
        return sum;
    }

    public Course getSubjectCourse() {
        return course;
    }

    public int getCreditHours() {
        return course.getCredits();
    }

    public ArrayList<Seat> getSeatlist() {
        return seatlist;
    }

    public int getEnrolledCount() {
        int count = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied()) {
                count++;
            }
        }
        return count;
    }
}
