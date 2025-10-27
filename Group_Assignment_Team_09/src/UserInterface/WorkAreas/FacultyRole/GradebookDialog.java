/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.CourseSchedule.SeatAssignment;
import Business.Profiles.StudentProfile;
import javax.swing.*;
import java.awt.*;

/**
 * Modal dialog for grading a student in a specific course
 *
 * @author sriya
 */
public class GradebookDialog extends JDialog {

    private GradebookPanel panel;

    /**
     * Constructor with student and seat assignment data
     */
    public GradebookDialog(Window owner, StudentProfile student, SeatAssignment sa, String courseId) {
        super(owner, "Gradebook - " + courseId, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 550);
        setLocationRelativeTo(owner);

        // Create panel with student data
        panel = new GradebookPanel(student, sa, courseId);
        add(panel);
    }

    /**
     * Old constructor for backward compatibility (creates empty gradebook)
     */
    public GradebookDialog(Window owner, String courseId, String studentId) {
        super(owner, "Gradebook", ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 550);
        setLocationRelativeTo(owner);

        panel = new GradebookPanel();
        add(panel);
    }
}
