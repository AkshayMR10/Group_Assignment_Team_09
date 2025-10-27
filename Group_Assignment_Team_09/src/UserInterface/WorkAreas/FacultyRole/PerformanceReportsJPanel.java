/*
 * Faculty Use Case - Performance Reports Panel
 * Displays grade distribution, average, and enrollment statistics
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.CourseSchedule.CourseOffer;
import Business.CourseSchedule.CourseSchedule;
import Business.CourseSchedule.Seat;
import Business.CourseSchedule.SeatAssignment;
import Business.Profiles.FacultyProfile;
import Business.UserAccounts.UserAccount;
import java.util.Map;

/**
 *
 * @author sriya
 */
public class PerformanceReportsJPanel extends javax.swing.JPanel {

    private final Business business;
    private final UserAccount currentUser;
    private FacultyProfile myFaculty;

    /**
     * Creates new form PerformanceReportsJPanel
     */
    public PerformanceReportsJPanel(Business b, UserAccount u) {
        this.business = b;
        this.currentUser = u;
        this.myFaculty = (FacultyProfile) currentUser.getAssociatedPersonProfile();
        initComponents();
        initTable();
        loadSemesters();

        // Add listener to semester dropdown
        cmbSemester.addActionListener(e -> loadCoursesForSelectedSemester());

        // Load courses for initial semester
        if (cmbSemester.getItemCount() > 0) {
            loadCoursesForSelectedSemester();
        }
    }

    private void initTable() {
        tblDistribution.setModel(new javax.swing.table.DefaultTableModel(
                new Object[]{"Letter Grade", "Student Count"}, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
    }

    private void loadSemesters() {
        cmbSemester.removeAllItems();
        for (String sem : business.getMasterCourseCatalog().keySet()) {
            cmbSemester.addItem(sem);
        }
    }

    /**
     * Load courses for the selected semester that this faculty teaches
     */
    private void loadCoursesForSelectedSemester() {
        cmbCourse.removeAllItems();

        String selectedSemester = (String) cmbSemester.getSelectedItem();
        if (selectedSemester == null) {
            return;
        }

        CourseSchedule cs = business.getCourseSchedule(selectedSemester);
        if (cs == null) {
            return;
        }

        // Add only courses taught by this faculty
        for (CourseOffer co : cs.getSchedule()) {
            if (co.getFacultyProfile() != null && co.getFacultyProfile().equals(myFaculty)) {
                String displayText = co.getCourseNumber() + " - " + co.getSubjectCourse().getName();
                cmbCourse.addItem(displayText);
            }
        }

        // Auto-select first course if available
        if (cmbCourse.getItemCount() > 0) {
            cmbCourse.setSelectedIndex(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCourse = new javax.swing.JComboBox<>();
        cmbSemester = new javax.swing.JComboBox<>();
        btnRun = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDistribution = new javax.swing.JTable();
        lblAverage = new javax.swing.JLabel();
        lblEnrollment = new javax.swing.JLabel();
        btnExportCSV = new javax.swing.JButton();

        cmbCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnRun.setText("Run");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        tblDistribution.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Grade", "Count"
            }
        ));
        jScrollPane1.setViewportView(tblDistribution);

        lblAverage.setText("Average:");

        lblEnrollment.setText("Enrollment:");

        btnExportCSV.setText("ExportCSV");
        btnExportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(btnExportCSV))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAverage, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)
                                .addComponent(lblEnrollment, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnRun)))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRun))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAverage)
                    .addComponent(lblEnrollment))
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnExportCSV)
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        String sem = (String) cmbSemester.getSelectedItem();
        if (sem == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Please select a semester.",
                    "No Semester Selected",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cmbCourse.getSelectedIndex() < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Please select a course.",
                    "No Course Selected",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Find the selected CourseOffer
        CourseOffer selectedCourse = null;
        CourseSchedule cs = business.getCourseSchedule(sem);

        if (cs == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No course schedule found for " + sem,
                    "Data Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedCourseText = (String) cmbCourse.getSelectedItem();

        for (CourseOffer co : cs.getSchedule()) {
            if (co.getFacultyProfile() != null && co.getFacultyProfile().equals(myFaculty)) {
                String courseText = co.getCourseNumber() + " - " + co.getSubjectCourse().getName();
                if (courseText.equals(selectedCourseText)) {
                    selectedCourse = co;
                    break;
                }
            }
        }

        if (selectedCourse == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Could not find course data.",
                    "Data Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Initialize grade distribution map
        java.util.Map<String, Integer> gradeDistribution = new java.util.LinkedHashMap<>();
        String[] gradeLetters = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
        for (String grade : gradeLetters) {
            gradeDistribution.put(grade, 0);
        }

        int enrollmentCount = 0;
        double totalGpa = 0.0;

        // Process each enrolled student
        for (Seat seat : selectedCourse.getSeatlist()) {
            if (!seat.isOccupied()) {
                continue;
            }

            // ✅ FIX: Use correct method name
            SeatAssignment sa = seat.getSeatassignment();  // Lowercase 'a'
            if (sa == null) {
                continue;
            }

            // Get grade (use field directly or getter if you added it)
            float gpa = sa.grade;  // or sa.getGrade() if you added the getter

            enrollmentCount++;
            totalGpa += gpa;

            // Convert GPA to letter grade
            String letterGrade = toLetterFromGpa(gpa);

            // Increment count for this grade
            if (gradeDistribution.containsKey(letterGrade)) {
                gradeDistribution.put(letterGrade, gradeDistribution.get(letterGrade) + 1);
            }
        }

        // Populate grade distribution table
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblDistribution.getModel();
        model.setRowCount(0);

        for (Map.Entry<String, Integer> entry : gradeDistribution.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        // Calculate and display average
        double averagePercentage = (enrollmentCount == 0) ? 0.0 : (totalGpa / enrollmentCount) / 4.0 * 100.0;
        double averageGpa = (enrollmentCount == 0) ? 0.0 : (totalGpa / enrollmentCount);

        lblAverage.setText(String.format("Average: %.2f%% (GPA: %.2f)", averagePercentage, averageGpa));
        lblEnrollment.setText("Enrollment: " + enrollmentCount + " students");

        // Show message if no students enrolled
        if (enrollmentCount == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No students enrolled in this course yet.",
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
    }//GEN-LAST:event_btnRunActionPerformed

    private void btnExportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportCSVActionPerformed
        // Check if report has been generated
        if (tblDistribution.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Please generate a report first by clicking 'Generate Report'.",
                    "No Data",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setDialogTitle("Save Performance Report as CSV");
        fc.setSelectedFile(new java.io.File("performance_report.csv"));

        if (fc.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            try (java.io.PrintWriter pw = new java.io.PrintWriter(fc.getSelectedFile())) {
                // Write header
                pw.println("Grade Distribution Report");
                pw.println("Course," + cmbCourse.getSelectedItem());
                pw.println("Semester," + cmbSemester.getSelectedItem());
                pw.println("Average," + lblAverage.getText().replace("Average: ", ""));
                pw.println("Enrollment," + lblEnrollment.getText().replace("Enrollment: ", ""));
                pw.println();
                pw.println("Letter Grade,Student Count");

                // Write data
                javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) tblDistribution.getModel();
                for (int i = 0; i < m.getRowCount(); i++) {
                    pw.println(m.getValueAt(i, 0) + "," + m.getValueAt(i, 1));
                }

                javax.swing.JOptionPane.showMessageDialog(this,
                        "Report exported successfully!\n\n"
                        + "File: " + fc.getSelectedFile().getName(),
                        "Export Successful",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error exporting file:\n" + ex.getMessage(),
                        "Export Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExportCSVActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportCSV;
    private javax.swing.JButton btnRun;
    private javax.swing.JComboBox<String> cmbCourse;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAverage;
    private javax.swing.JLabel lblEnrollment;
    private javax.swing.JTable tblDistribution;
    // End of variables declaration//GEN-END:variables
}
