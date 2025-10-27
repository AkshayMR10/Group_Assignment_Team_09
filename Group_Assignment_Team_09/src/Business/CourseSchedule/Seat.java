/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.CourseSchedule;

public class Seat {

    Boolean occupied;
    int number;
    SeatAssignment seatassignment; // links back to student profile
    CourseOffer courseoffer;

    public Seat(CourseOffer co, int n) {
        courseoffer = co;
        number = n;
        occupied = false;
    }

    public Boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occ) {
        this.occupied = occ;
    }

    public SeatAssignment getSeatAssignment() {
        return seatassignment;
    }

    public SeatAssignment newSeatAssignment(CourseLoad cl) {
        seatassignment = new SeatAssignment(cl, this); // links seat assignment to seat
        occupied = true;
        return seatassignment;
    }

    public CourseOffer getCourseOffer() {
        return courseoffer;
    }

    public int getCourseCredits() {
        return courseoffer.getCreditHours();
    }

    public SeatAssignment getSeatassignment() {
        return seatassignment;
    }
}
