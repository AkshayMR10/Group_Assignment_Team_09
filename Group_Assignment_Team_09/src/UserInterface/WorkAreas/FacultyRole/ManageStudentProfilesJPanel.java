/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.CourseSchedule.CourseLoad;
import Business.CourseSchedule.CourseOffer;
import Business.CourseSchedule.CourseSchedule;
import Business.CourseSchedule.Seat;
import Business.CourseSchedule.SeatAssignment;
import Business.Person.Person;
import Business.Profiles.FacultyProfile;
import Business.Profiles.StudentProfile;
import Business.UserAccounts.UserAccount;

/**
 *
 * @author sriya
 */
public class ManageStudentProfilesJPanel extends javax.swing.JPanel {

    private final Business business;
    private final UserAccount currentUser;
    private FacultyProfile myFaculty;
    private java.util.List<CourseOffer> myOffers = new java.util.ArrayList<>();

    /**
     * Creates new form ManageStudentProfilesJPanel
     */
    public ManageStudentProfilesJPanel(Business b, UserAccount u) {
        this.business = b;
        this.currentUser = u;
        this.myFaculty = (FacultyProfile) currentUser.getAssociatedPersonProfile();
        initComponents();
        initRosterTable();
        loadCoursesCombo();              // fills cmbCourse using your Business model
        // Optional: auto-load first course on open
        if (cmbCourse.getItemCount() > 0) {
            populateRosterForSelectedCourse();
        }
        cmbCourse.addActionListener(e -> populateRosterForSelectedCourse());
    }

    private void initRosterTable() {
        tblRoster.setModel(new javax.swing.table.DefaultTableModel(
                new Object[]{"StudentId", "Name", "Email", "Progress%", "Letter"}, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
    }

// REPLACE/KEEP this method body
    private void loadCoursesCombo() {
        cmbCourse.removeAllItems();
        myOffers.clear();

        // Iterate all semesters and collect offers taught by me
        for (String sem : business.getMasterCourseCatalog().keySet()) {
            CourseSchedule cs = business.getCourseSchedule(sem);
            if (cs == null) {
                continue;
            }
            for (CourseOffer co : cs.getSchedule()) {
                if (co.getFacultyProfile() != null && co.getFacultyProfile() == myFaculty) {
                    myOffers.add(co);
                    cmbCourse.addItem(co.getSubjectCourse().toString() + " (" + sem + ")");
                }
            }
        }
    }

    private void populateRosterForSelectedCourse() {
        int idx = cmbCourse.getSelectedIndex();
        javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) tblRoster.getModel();
        m.setRowCount(0);

        if (idx < 0 || idx >= myOffers.size()) {
            return;
        }

        CourseOffer co = myOffers.get(idx);
        if (co == null || co.getSeatList() == null) {
            return;
        }

        // Iterate through all seats
        for (Seat seat : co.getSeatList()) {
            // Only process occupied seats
            if (!seat.isOccupied()) {
                continue;
            }

            // Get seat assignment
            SeatAssignment sa = seat.getSeatAssignment();
            if (sa == null) {
                continue;
            }

            // Get course load
            CourseLoad courseLoad = sa.getCourseload();
            if (courseLoad == null) {
                continue;
            }

            // Get student profile
            StudentProfile sp = courseLoad.getStudentProfile();
            if (sp == null) {
                System.out.println("[Warning] CourseLoad missing student reference");
                continue;
            }

            // Get person
            Person p = sp.getPerson();
            if (p == null) {
                continue;
            }

            // Get grade (it's a public field in SeatAssignment)
            float gradeGpa = sa.grade;  // 0.0 - 4.0 scale

            // Calculate percentage
            double percentage = (gradeGpa / 4.0) * 100.0;

            // Convert to letter
            String letter = toLetterFromGpa(gradeGpa);

            // Add row to table
            m.addRow(new Object[]{
                p.getUniversityId() != null ? p.getUniversityId() : "N/A",
                p.getName() != null ? p.getName() : "Unknown",
                p.getEmail() != null ? p.getEmail() : "N/A",
                String.format("%.1f%%", percentage),
                letter
            });
        }

        // Show message if no students
        if (m.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No students enrolled in this course.",
                    "No Enrollments",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Convert GPA to letter grade
     */
    private String toLetterFromGpa(float gpa) {
        if (gpa >= 4.0) {
            return "A";
        }
        if (gpa >= 3.7) {
            return "A-";
        }
        if (gpa >= 3.3) {
            return "B+";
        }
        if (gpa >= 3.0) {
            return "B";
        }
        if (gpa >= 2.7) {
            return "B-";
        }
        if (gpa >= 2.3) {
            return "C+";
        }
        if (gpa >= 2.0) {
            return "C";
        }
        if (gpa >= 1.7) {
            return "C-";
        }
        if (gpa >= 1.0) {
            return "D";
        }
        return "F";
    }

    /**
     * Small helper to call a zero-arg getter safely without NPEs.
     */
    private static <T> String safe(java.util.concurrent.Callable<T> c) {
        try {
            T v = c.call();
            return (v == null) ? "" : v.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCourse = new javax.swing.JLabel();
        cmbCourse = new javax.swing.JComboBox<>();
        btnLoad = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRoster = new javax.swing.JTable();
        btnOpenGradebook = new javax.swing.JButton();
        btnViewTranscript = new javax.swing.JButton();
        btnRankAndGPA = new javax.swing.JButton();

        lblCourse.setText("Course");

        cmbCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        tblRoster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "StudentId", "Name", "Email", "Progress%", "Letter"
            }
        ));
        jScrollPane1.setViewportView(tblRoster);

        btnOpenGradebook.setText("OpenGradeBook");
        btnOpenGradebook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenGradebookActionPerformed(evt);
            }
        });

        btnViewTranscript.setText("ViewTranscript");
        btnViewTranscript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewTranscriptActionPerformed(evt);
            }
        });

        btnRankAndGPA.setText("RankAndGPA");
        btnRankAndGPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRankAndGPAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(lblCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoad))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(btnOpenGradebook)
                        .addGap(28, 28, 28)
                        .addComponent(btnViewTranscript)
                        .addGap(18, 18, 18)
                        .addComponent(btnRankAndGPA))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad)
                    .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCourse))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOpenGradebook)
                    .addComponent(btnViewTranscript)
                    .addComponent(btnRankAndGPA))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        populateRosterForSelectedCourse();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnOpenGradebookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenGradebookActionPerformed
        // TODO add your handling code here:
        // Validate selections
        int rowIndex = tblRoster.getSelectedRow();
        int courseIndex = cmbCourse.getSelectedIndex();

        if (courseIndex < 0 || courseIndex >= myOffers.size()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Please select a course from the dropdown.",
                    "No Course Selected",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (rowIndex < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Please select a student from the table.",
                    "No Student Selected",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the selected course
        CourseOffer selectedCourse = myOffers.get(courseIndex);

        // Get the student ID from the table
        String studentUnivId = tblRoster.getValueAt(rowIndex, 0).toString();

        // Find the student and their seat assignment in this course
        StudentProfile selectedStudent = null;
        SeatAssignment selectedSeatAssignment = null;

        for (Seat seat : selectedCourse.getSeatList()) {
            if (!seat.isOccupied()) {
                continue;
            }

            SeatAssignment sa = seat.getSeatAssignment();
            if (sa == null) {
                continue;
            }

            CourseLoad cl = sa.getCourseload();
            if (cl == null) {
                continue;
            }

            StudentProfile sp = cl.getStudentProfile();
            if (sp != null && sp.getPerson() != null) {
                if (sp.getPerson().getUniversityId().equals(studentUnivId)) {
                    selectedStudent = sp;
                    selectedSeatAssignment = sa;
                    break;
                }
            }
        }

        // Validate we found the student
        if (selectedStudent == null || selectedSeatAssignment == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Could not find student enrollment data.",
                    "Data Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Open the gradebook dialog
        java.awt.Window owner = javax.swing.SwingUtilities.getWindowAncestor(this);
        String courseId = selectedCourse.getCourseNumber();

        GradebookDialog dialog = new GradebookDialog(owner, selectedStudent, selectedSeatAssignment, courseId);
        dialog.setVisible(true);

        // After dialog closes, refresh the roster to show updated grades
        populateRosterForSelectedCourse();

    }//GEN-LAST:event_btnOpenGradebookActionPerformed

    private void btnViewTranscriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewTranscriptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewTranscriptActionPerformed

    private void btnRankAndGPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRankAndGPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRankAndGPAActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnOpenGradebook;
    private javax.swing.JButton btnRankAndGPA;
    private javax.swing.JButton btnViewTranscript;
    private javax.swing.JComboBox<String> cmbCourse;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JTable tblRoster;
    // End of variables declaration//GEN-END:variables
}
