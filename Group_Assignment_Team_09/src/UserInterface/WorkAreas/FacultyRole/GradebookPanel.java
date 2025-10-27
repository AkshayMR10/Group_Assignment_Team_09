/*
 * Faculty Use Case - Gradebook Panel
 * Allows faculty to grade individual assignments and compute final letter grade
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.CourseSchedule.SeatAssignment;
import Business.Person.Person;
import Business.Profiles.StudentProfile;
import javax.swing.JOptionPane;

/**
 *
 * @author sriya
 */
public class GradebookPanel extends javax.swing.JPanel {

    private StudentProfile student;
    private SeatAssignment seatAssignment;
    private String courseId;

    /**
     * Default constructor (for GUI builder)
     */
    public GradebookPanel() {
        initComponents();
    }

    /**
     * Constructor with student and course data
     */
    public GradebookPanel(StudentProfile sp, SeatAssignment sa, String cid) {
        this.student = sp;
        this.seatAssignment = sa;
        this.courseId = cid;
        initComponents();

        // Display student info
        if (student != null && student.getPerson() != null) {
            Person p = student.getPerson();
            lblHeading.setText("Gradebook for " + p.getName() + " - " + courseId);
        }

        // Load existing grade if available
        if (seatAssignment != null) {
            float existingGrade = seatAssignment.getGrade();
            if (existingGrade > 0) {
                double percentage = (existingGrade / 4.0) * 100.0;
                lblTotal.setText(String.format("Current Grade: %.2f%%   Letter: %s",
                        percentage, toLetterFromGpa(existingGrade)));
            }
        }
    }

    private String toLetter(double percentage) {
        if (percentage >= 93) {
            return "A";
        }
        if (percentage >= 90) {
            return "A-";
        }
        if (percentage >= 87) {
            return "B+";
        }
        if (percentage >= 83) {
            return "B";
        }
        if (percentage >= 80) {
            return "B-";
        }
        if (percentage >= 77) {
            return "C+";
        }
        if (percentage >= 73) {
            return "C";
        }
        if (percentage >= 70) {
            return "C-";
        }
        if (percentage >= 60) {
            return "D";
        }
        return "F";
    }

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
     * Convert percentage to GPA (0.0 - 4.0 scale)
     */
    private float percentageToGpa(double percentage) {
        String letter = toLetter(percentage);
        switch (letter) {
            case "A":
                return 4.0f;
            case "A-":
                return 3.7f;
            case "B+":
                return 3.3f;
            case "B":
                return 3.0f;
            case "B-":
                return 2.7f;
            case "C+":
                return 2.3f;
            case "C":
                return 2.0f;
            case "C-":
                return 1.7f;
            case "D":
                return 1.0f;
            default:
                return 0.0f;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeading = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        btnAddRow = new javax.swing.JButton();
        btnDeleteRow = new javax.swing.JButton();
        btnRecalc = new javax.swing.JButton();
        btnPostFinal = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        lblHeading.setText("GradeBook for Student");

        tblGrades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Assignment", "Max", "Weight (0-1)", "Score"
            }
        ));
        jScrollPane1.setViewportView(tblGrades);

        btnAddRow.setText("Add Row");
        btnAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRowActionPerformed(evt);
            }
        });

        btnDeleteRow.setText("Delete Row");
        btnDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRowActionPerformed(evt);
            }
        });

        btnRecalc.setText("ReCalculate");
        btnRecalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecalcActionPerformed(evt);
            }
        });

        btnPostFinal.setText("Post Final");
        btnPostFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostFinalActionPerformed(evt);
            }
        });

        lblTotal.setText("Total: 0 % Letter: -");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(lblHeading))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnAddRow)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteRow)
                        .addGap(18, 18, 18)
                        .addComponent(btnRecalc)
                        .addGap(18, 18, 18)
                        .addComponent(btnPostFinal)
                        .addGap(26, 26, 26)
                        .addComponent(lblTotal)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRow)
                    .addComponent(btnDeleteRow)
                    .addComponent(btnRecalc)
                    .addComponent(btnPostFinal)
                    .addComponent(lblTotal))
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRowActionPerformed
        ((javax.swing.table.DefaultTableModel) tblGrades.getModel())
                .addRow(new Object[]{"New Assignment", 100.0, 0.1, 0.0});
    }//GEN-LAST:event_btnAddRowActionPerformed


    private void btnRecalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecalcActionPerformed
        javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) tblGrades.getModel();
        double totalPercentage = 0;

        try {
            for (int i = 0; i < m.getRowCount(); i++) {
                double max = Double.parseDouble(m.getValueAt(i, 1).toString());
                double weight = Double.parseDouble(m.getValueAt(i, 2).toString());
                double score = Double.parseDouble(m.getValueAt(i, 3).toString());

                if (max > 0) {
                    totalPercentage += (score / max) * weight * 100.0;
                }
            }

            String letter = toLetter(totalPercentage);
            lblTotal.setText(String.format("Total: %.2f%%   Letter: %s", totalPercentage, letter));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number format in table. Please check your entries.",
                    "Calculation Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRecalcActionPerformed

    private void btnDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRowActionPerformed
        int r = tblGrades.getSelectedRow();
        if (r >= 0) {
            ((javax.swing.table.DefaultTableModel) tblGrades.getModel()).removeRow(r);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteRowActionPerformed

    private void btnPostFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostFinalActionPerformed
        // First, calculate the final grade
        // Recalculate first
        btnRecalcActionPerformed(evt);

        // Extract percentage
        String labelText = lblTotal.getText();
        try {
            String percentStr = labelText.split("%")[0].split(":")[1].trim();
            double percentage = Double.parseDouble(percentStr);

            // Convert to GPA (0.0 - 4.0)
            float gpa = percentageToGpa(percentage);

            // SAVE to SeatAssignment
            if (seatAssignment != null) {
                seatAssignment.setGrade(gpa);  // ✅ This persists the grade!

                JOptionPane.showMessageDialog(this,
                        "Final grade posted!\n\n"
                        + "Percentage: " + String.format("%.2f%%", percentage) + "\n"
                        + "Letter: " + toLetter(percentage) + "\n"
                        + "GPA: " + String.format("%.2f", gpa),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error posting grade", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPostFinalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRow;
    private javax.swing.JButton btnDeleteRow;
    private javax.swing.JButton btnPostFinal;
    private javax.swing.JButton btnRecalc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblGrades;
    // End of variables declaration//GEN-END:variables
}
