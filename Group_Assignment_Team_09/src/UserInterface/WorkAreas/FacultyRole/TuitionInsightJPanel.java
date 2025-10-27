/*
 * Faculty Use Case - Tuition Insight Panel
 * Shows tuition revenue collected from enrolled students in faculty's courses
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.CourseSchedule.CourseOffer;
import Business.CourseSchedule.CourseSchedule;
import Business.Profiles.FacultyProfile;
import Business.UserAccounts.UserAccount;

/**
 *
 * @author sriya
 */
public class TuitionInsightJPanel extends javax.swing.JPanel {

    private final Business business;
    private final UserAccount currentUser;
    private FacultyProfile myFaculty;

    /**
     * Creates new form TuitionInsightJPanel
     */
    public TuitionInsightJPanel(Business b, UserAccount u) {
        this.business = b;
        this.currentUser = u;
        this.myFaculty = (FacultyProfile) currentUser.getAssociatedPersonProfile();
        initComponents();
        initTable();
        loadSemesters();

        // ✅ FIX: Add semester change listener
        cmbSemester.addActionListener(e -> refreshTuitionData());

        // ✅ FIX: Load data on startup
        if (cmbSemester.getItemCount() > 0) {
            refreshTuitionData();
        }
    }

    private void initTable() {
        tblTuition.setModel(new javax.swing.table.DefaultTableModel(
                new Object[]{"Course ID", "Course Title", "Enrolled", "Revenue"}, 0) {
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

        // Select first semester by default
        if (cmbSemester.getItemCount() > 0) {
            cmbSemester.setSelectedIndex(0);
        }
    }

    /**
     * Refresh tuition data for selected semester
     */
    private void refreshTuitionData() {
        String semester = (String) cmbSemester.getSelectedItem();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblTuition.getModel();
        model.setRowCount(0);

        if (semester == null) {
            lblTotalCollected.setText("Total Collected: $0");
            return;
        }

        CourseSchedule cs = business.getCourseSchedule(semester);
        if (cs == null) {
            lblTotalCollected.setText("Total Collected: $0");
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No course schedule found for " + semester,
                    "No Data",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int totalRevenue = 0;
        int coursesFound = 0;

        // Iterate through all course offers in this semester
        for (CourseOffer co : cs.getSchedule()) {
            // Only show courses taught by this faculty member
            if (co.getFacultyProfile() != null && co.getFacultyProfile().equals(myFaculty)) {
                // ✅ FIX: Use correct method name
                String courseId = co.getCourseNumber();
                String courseName = co.getSubjectCourse().getName();
                int enrolled = co.getEnrolledCount();
                int revenue = co.getTotalCourseRevenues();

                totalRevenue += revenue;
                coursesFound++;

                // Add row to table
                model.addRow(new Object[]{
                    courseId,
                    courseName,
                    enrolled,
                    String.format("$%,d", revenue)
                });
            }
        }

        // Update total label
        lblTotalCollected.setText(String.format("Total Collected: $%,d", totalRevenue));

        // Show message if no courses found
        /*if (coursesFound == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "You have no courses assigned for " + semester + "\n\n"
                    + "Contact the administrator to be assigned to courses.",
                    "No Assigned Courses",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbSemester = new javax.swing.JComboBox<>();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTuition = new javax.swing.JTable();
        lblTotalCollected = new javax.swing.JLabel();

        cmbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tblTuition.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CourseId", "Title ", "Enrolled", "Paid Tuition"
            }
        ));
        jScrollPane1.setViewportView(tblTuition);

        lblTotalCollected.setText("Tota Collected: $");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnRefresh))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(lblTotalCollected)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblTotalCollected)
                .addContainerGap(129, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshTuitionData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalCollected;
    private javax.swing.JTable tblTuition;
    // End of variables declaration//GEN-END:variables
}
