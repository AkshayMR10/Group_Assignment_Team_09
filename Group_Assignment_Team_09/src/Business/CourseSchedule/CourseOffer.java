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
import Business.Profiles.StudentProfile;
import java.util.ArrayList;



public class CourseOffer {

    Course course;
    ArrayList<Seat> seatlist;
    FacultyProfile facultyProfile;

    private int capacity;
    private String room;
    private String schedule;

    public CourseOffer(Course c) {
        this.course = c;
        this.seatlist = new ArrayList<>();
    }

    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setRoom(String room) { this.room = room; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public Course getCourse() { return course; }
    public int getCapacity() { return capacity; }
    public String getRoom() { return room; }
    public String getSchedule() { return schedule; }

    public void AssignAsTeacher(FacultyProfile fp) {
        this.facultyProfile = fp;
    }

    public FacultyProfile getFacultyProfile() {
        return facultyProfile;
    }

    public String getCourseNumber() {
        return course.getCourseNumber();
    }

    // ✅ keep this method name as in your project
    public void generatSeats(int n) {
        seatlist.clear();
        for (int i = 0; i < n; i++) {
            seatlist.add(new Seat(this, i));
        }
    }

    public Seat getEmptySeat() {
        for (Seat s : seatlist) {
            if (!s.isOccupied()) return s;
        }
        return null;
    }

    public SeatAssignment assignEmptySeat(CourseLoad cl) {
        for (Seat s : seatlist) {
            if (!s.isOccupied()) {
                SeatAssignment sa = new SeatAssignment(cl, s);
                s.setSeatAssignment(sa);
                return sa;
            }
        }
        return null;
    }

    public void dropStudentById(String studentId) {
        for (Seat s : seatlist) {
            if (s.isOccupied() && s.getSeatAssignment() != null) {
                StudentProfile sp = s.getSeatAssignment().getStudentProfile();
                if (sp != null && sp.getPerson().getPersonId().equals(studentId)) {
                    s.setOccupied(false);
                    s.setSeatAssignment(null);
                    break;
                }
            }
        }
    }

    public int getTotalCourseRevenues() {
        int sum = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied()) sum += course.getCoursePrice();
        }
        return sum;
    }

    public Course getSubjectCourse() { return course; }

    public ArrayList<Seat> getSeatList() { return seatlist; }

    public int getEnrolledCount() {
        int count = 0;
        for (Seat s : seatlist) if (s.isOccupied()) count++;
        return count;
    }

    public int getCreditHours() { return course.getCredits(); }

    public String getCourseId() { return course.getCourseNumber(); }

    public String getCourseTitle() { return course.getName(); }
}
