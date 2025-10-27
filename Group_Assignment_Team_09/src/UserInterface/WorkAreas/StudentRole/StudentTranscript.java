/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.StudentRole;

import Business.Profiles.StudentProfile;
import Business.CourseSchedule.CourseLoad;
import Business.CourseSchedule.SeatAssignment;
import StudentUtils.GradeUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
/**
 *
 * @author Amrin
 */
public class StudentTranscript extends JPanel {

    private StudentProfile student;
    private JTable tblTranscript;
    private JComboBox<String> cmbSemesterFilter;
    private JTextField fieldSemesterGPA, fieldOverallGPA, fieldStanding;

    public StudentTranscript(StudentProfile student) {
        this.student = student;
        initComponents();
        populateSemesters();
        populateTable(null); // show all semesters initially

        // Listener for semester selection
        comboterm.addActionListener(e -> {
            String selectedSemester = (String) comboterm.getSelectedItem();
            populateTable(selectedSemester.equals("All Semesters") ? null : selectedSemester);
        });
    }

    // Populate comboterm with semesters
    private void populateSemesters() {
        Set<String> semesterSet = student.getCourseLoadList().keySet();
        comboterm.removeAllItems();
        comboterm.addItem("All Semesters");
        for (String semester : semesterSet) {
            comboterm.addItem(semester);
        }
    }

    // Populate the transcript table
    private void populateTable(String semesterFilter) {
        DefaultTableModel model = (DefaultTableModel) tbltrans.getModel();
        model.setRowCount(0);

        double totalQualityPointsAll = 0;
        int totalCreditsAll = 0;

        Map<String, Double> semesterQualityPoints = new HashMap<>();
        Map<String, Integer> semesterCredits = new HashMap<>();

        for (CourseLoad cl : student.getCourseLoadList().values()) {
            String semester = cl.getSemester();
            for (SeatAssignment sa : cl.getSeatAssignments()) {
                float grade = sa.getGrade();
                int credits = sa.getCreditHours();
                double qualityPoints = grade * credits;

                totalQualityPointsAll += qualityPoints;
                totalCreditsAll += credits;

                semesterQualityPoints.put(semester, semesterQualityPoints.getOrDefault(semester, 0.0) + qualityPoints);
                semesterCredits.put(semester, semesterCredits.getOrDefault(semester, 0) + credits);

                if (semesterFilter == null || semester.equals(semesterFilter)) {
                    model.addRow(new Object[]{
                            semester,
                            sa.getCourseId(),
                            sa.getCourseName(),
                            GradeUtils.getLetterGrade(grade),
                            "", "", "" // placeholders for GPA and standing
                    });
                }
            }
        }

        double overallGPA = totalCreditsAll > 0 ? totalQualityPointsAll / totalCreditsAll : 0;

        // Fill Term GPA and Standing in table
        for (int i = 0; i < model.getRowCount(); i++) {
            String semester = (String) model.getValueAt(i, 0);
            double termGPA = semesterCredits.get(semester) > 0
                    ? semesterQualityPoints.get(semester) / semesterCredits.get(semester)
                    : 0;
            model.setValueAt(String.format("%.2f", termGPA), i, 4);
            model.setValueAt(String.format("%.2f", overallGPA), i, 5);

            String standing = "Good Standing";
            if (overallGPA < 3.0) standing = "Academic Probation";
            else if (termGPA < 3.0) standing = "Academic Warning";
            model.setValueAt(standing, i, 6);
        }

        // Update bottom fields
        if (semesterFilter != null) {
            double termGPA = semesterCredits.get(semesterFilter) > 0
                    ? semesterQualityPoints.get(semesterFilter) / semesterCredits.get(semesterFilter)
                    : 0;
            fieldgpa.setText(String.format("%.2f", termGPA));
            fieldogpa.setText(String.format("%.2f", overallGPA));

            String standing = "Good Standing";
            if (overallGPA < 3.0) standing = "Academic Probation";
            else if (termGPA < 3.0) standing = "Academic Warning";
            fieldstand.setText(standing);
        } else {
            fieldgpa.setText("");
            fieldogpa.setText(String.format("%.2f", overallGPA));
            fieldstand.setText(overallGPA >= 3.0 ? "Good Standing" : "Academic Probation");
        }
    }
    public void refreshTable() {
    populateTable(null); // null = show all semesters
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnback = new javax.swing.JButton();
        lbltitle = new javax.swing.JLabel();
        lblSemester = new javax.swing.JLabel();
        comboterm = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltrans = new javax.swing.JTable();
        lbltermg = new javax.swing.JLabel();
        fieldgpa = new javax.swing.JTextField();
        lblogpa = new javax.swing.JLabel();
        fieldogpa = new javax.swing.JTextField();
        lblaca = new javax.swing.JLabel();
        fieldstand = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 204, 204));

        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        lbltitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbltitle.setText("TRANSCRIPT REVIEW");

        lblSemester.setText("Select Semester");

        comboterm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboterm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combotermActionPerformed(evt);
            }
        });

        tbltrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Term", "Course ID", "Course Name", "Grade", "Term GPA", "Overall GPA", "Standing"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbltrans);

        lbltermg.setText("Term GPA");

        lblogpa.setText("Overall GPA");

        fieldogpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldogpaActionPerformed(evt);
            }
        });

        lblaca.setText("Academic Standing");

        fieldstand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldstandActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lbltermg)
                .addGap(18, 18, 18)
                .addComponent(fieldgpa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblogpa)
                .addGap(18, 18, 18)
                .addComponent(fieldogpa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblSemester)
                                .addGap(26, 26, 26)
                                .addComponent(comboterm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnback)
                                .addGap(122, 122, 122)
                                .addComponent(lbltitle))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(lblaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldstand, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnback)
                    .addComponent(lbltitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSemester)
                    .addComponent(comboterm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltermg)
                    .addComponent(fieldgpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblogpa)
                    .addComponent(fieldogpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblaca)
                    .addComponent(fieldstand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) this.getParent().getLayout();
        layout.previous(this.getParent());
    }//GEN-LAST:event_btnbackActionPerformed

    private void combotermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combotermActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combotermActionPerformed

    private void fieldogpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldogpaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldogpaActionPerformed

    private void fieldstandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldstandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldstandActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JComboBox<String> comboterm;
    private javax.swing.JTextField fieldgpa;
    private javax.swing.JTextField fieldogpa;
    private javax.swing.JTextField fieldstand;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblaca;
    private javax.swing.JLabel lblogpa;
    private javax.swing.JLabel lbltermg;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JTable tbltrans;
    // End of variables declaration//GEN-END:variables
}
