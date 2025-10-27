/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.StudentRole;

import Business.Business;
import Business.CourseSchedule.CourseLoad;
import Business.CourseSchedule.CourseOffer;
import Business.CourseSchedule.CourseSchedule;
import Business.CourseSchedule.Seat;
import Business.CourseSchedule.SeatAssignment;
import Business.Profiles.StudentProfile;
import ManageStudentModel.CourseCatalog;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amrin
 */
public class StudentRegistration extends javax.swing.JPanel {

    private DefaultTableModel offeringModel;
    private DefaultTableModel registeredModel;
    private List<CourseCatalog> allCourses = new ArrayList<>();
    private List<CourseCatalog> registeredCourses = new ArrayList<>();
    private int totalCredits = 0;

    private JPanel container;
    private StudentProfile student;
    private Business business;

private StudentGraduation gradPanel;
private StudentTranscript transcriptPanel;
private StudentFinance financePanel;

public StudentRegistration(JPanel container, StudentProfile student, Business business,
                           StudentGraduation gradPanel,
                           StudentTranscript transcriptPanel,
                           StudentFinance financePanel) {
    this.student = student;
    this.business = business;
    this.container = container;
    this.gradPanel = gradPanel;
    this.transcriptPanel = transcriptPanel;
    this.financePanel = financePanel;
    initComponents();
    setupTables();
    loadAllCourses(business);
    populateComboBoxes();
    populateOfferTable(allCourses);
}

    private void setupTables() {
        offeringModel = (DefaultTableModel) Courseofferingtable.getModel();
        registeredModel = (DefaultTableModel) Courseregistertable.getModel();
    }

private void loadAllCourses(Business business) {
    allCourses.clear();
    for (CourseSchedule schedule : business.getMasterCourseCatalog().values()) {
        for (CourseOffer offer : schedule.getSchedule()) {
            String instructor = offer.getFacultyProfile() != null
                                ? offer.getFacultyProfile().getPerson().getName()
                                : "TBD";

            allCourses.add(new CourseCatalog(
                offer.getSubjectCourse().getCourseNumber(),
                offer.getSubjectCourse().getCourseName(),
                instructor,
                offer.getCreditHours(),
                schedule.getSemester() // make sure CourseSchedule has semester
            ));
        }
    }
}

private void populateComboBoxes() {
    combosem.removeAllItems();
    combosem.addItem("All"); // option to show all courses

    // Add only Fall and Spring options
    combosem.addItem("Fall");
    combosem.addItem("Spring");

    // Populate the search type combo box
    combocourseid.removeAllItems();
    combocourseid.addItem("Course ID");
    combocourseid.addItem("Course Name");
    combocourseid.addItem("Instructor");
}

    private void populateOfferTable(List<CourseCatalog> list) {
        offeringModel.setRowCount(0);
        for (CourseCatalog c : list) {
            offeringModel.addRow(new Object[]{
                c.getCourseId(),
                c.getCourseName(),
                c.getInstructor(),
                c.getCreditHours(),
                c.getSemester()
            });
        }
    }

    private void populateRegisterTable() {
        registeredModel.setRowCount(0);
        for (CourseCatalog c : registeredCourses) {
            registeredModel.addRow(new Object[]{
                c.getCourseId(),
                c.getCourseName(),
                c.getInstructor(),
                c.getCreditHours(),
                c.getSemester(),
                "Enrolled"
            });
        }
    }

private void searchCourses() {
    String semester = ((String) combosem.getSelectedItem()).trim();
    String searchBy = ((String) combocourseid.getSelectedItem()).trim().toLowerCase();
    String searchText = fieldsearch.getText().trim().toLowerCase();

    List<CourseCatalog> filtered = allCourses.stream()
            .filter(c -> {
                if (semester.equalsIgnoreCase("All")) return true;
                if (semester.equalsIgnoreCase("Fall")) return c.getSemester() != null && c.getSemester().toLowerCase().contains("fall");
                if (semester.equalsIgnoreCase("Spring")) return c.getSemester() != null && c.getSemester().toLowerCase().contains("spring");
                return true;
            })
            .filter(c -> {
                if (searchText.isEmpty()) return true;
                switch (searchBy) {
                    case "course id":
                        return c.getCourseId() != null && c.getCourseId().toLowerCase().contains(searchText);
                    case "course name":
                        return c.getCourseName() != null && c.getCourseName().toLowerCase().contains(searchText);
                    case "instructor":
                        return c.getInstructor() != null && c.getInstructor().toLowerCase().contains(searchText);
                    default:
                        return true;
                }
            })
            .collect(Collectors.toList());

    populateOfferTable(filtered);

    if (filtered.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No courses found for your search criteria.");
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

        jPanel1 = new javax.swing.JPanel();
        btnback = new javax.swing.JButton();
        lbltitle = new javax.swing.JLabel();
        lblsem = new javax.swing.JLabel();
        combosem = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        combocourseid = new javax.swing.JComboBox<>();
        btnsearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Courseofferingtable = new javax.swing.JTable();
        btnEnroll = new javax.swing.JButton();
        btndrop = new javax.swing.JButton();
        lblcourseoffering = new javax.swing.JLabel();
        lblregistercourse = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Courseregistertable = new javax.swing.JTable();
        lblcredits = new javax.swing.JLabel();
        fieldcredits = new javax.swing.JTextField();
        fieldsearch = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        lbltitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbltitle.setText("STUDENT REGISTRATION");

        lblsem.setText("Choose Semester:");

        combosem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Search By:");

        combocourseid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        Courseofferingtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Instructor", "Credit", "Semester"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Courseofferingtable);

        btnEnroll.setText("Enroll");
        btnEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrollActionPerformed(evt);
            }
        });

        btndrop.setText("Drop");
        btndrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndropActionPerformed(evt);
            }
        });

        lblcourseoffering.setText("Course Offerings Table");

        lblregistercourse.setText("Current Registered Courses");

        Courseregistertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Instructor", "Credit", "Semester", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Courseregistertable);

        lblcredits.setText("Total Enrolled Credits:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnback)
                        .addGap(106, 106, 106)
                        .addComponent(lbltitle))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblsem)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combosem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(combocourseid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fieldsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnsearch)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnEnroll)
                        .addGap(226, 226, 226)
                        .addComponent(btndrop))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lblcredits)
                        .addGap(18, 18, 18)
                        .addComponent(fieldcredits, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(lblregistercourse))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(lblcourseoffering, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltitle)
                    .addComponent(btnback))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblsem)
                    .addComponent(combosem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combocourseid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch)
                    .addComponent(fieldsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(lblcourseoffering)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnroll)
                    .addComponent(btndrop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblregistercourse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcredits)
                    .addComponent(fieldcredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) this.getParent().getLayout();
        layout.previous(this.getParent());
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnEnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrollActionPerformed
        // TODO add your handling code here:
    int selectedRow = Courseofferingtable.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a course to enroll.");
        return;
    }

    // Get course details from the table
    String courseId = Courseofferingtable.getValueAt(selectedRow, 0).toString();
    String courseName = Courseofferingtable.getValueAt(selectedRow, 1).toString();
    String instructor = Courseofferingtable.getValueAt(selectedRow, 2).toString();
    int credit = Integer.parseInt(Courseofferingtable.getValueAt(selectedRow, 3).toString());
    String semester = Courseofferingtable.getValueAt(selectedRow, 4).toString();

    // Check if already enrolled
    boolean alreadyEnrolled = registeredCourses.stream()
            .anyMatch(c -> c.getCourseId().equalsIgnoreCase(courseId) 
                        && c.getSemester().equalsIgnoreCase(semester));

    if (alreadyEnrolled) {
        JOptionPane.showMessageDialog(this, "You are already enrolled in this course.");
        return;
    }

    // Add course to registered list
    CourseCatalog newCourse = new CourseCatalog(courseId, courseName, instructor, credit, semester);
    registeredCourses.add(newCourse);

    // Update total credits
    totalCredits += credit;
    fieldcredits.setText(String.valueOf(totalCredits));

    // --------------------- Update finance ---------------------
    
    // Assuming $1000 per credit
    double fee = credit * 1000;
    student.addCourseFee(courseId, fee);

    // Refresh registration table
    populateRegisterTable();

    // Refresh finance panel
    if (financePanel != null) financePanel.refreshTable();

    // Refresh graduation and transcript tables
    if (gradPanel != null) gradPanel.refreshTable();
    if (transcriptPanel != null) transcriptPanel.refreshTable();

    JOptionPane.showMessageDialog(this, "Enrolled successfully in " + courseName + "!\nFee added: $" + String.format("%.2f", fee));
    }//GEN-LAST:event_btnEnrollActionPerformed

    private void btndropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndropActionPerformed
        // TODO add your handling code here:
        int selectedRow = Courseregistertable.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a course to drop.");
        return;
    }

    // Get course details from the table
    String courseId = Courseregistertable.getValueAt(selectedRow, 0).toString();
    String semester = Courseregistertable.getValueAt(selectedRow, 4).toString();

    // Find the course in registeredCourses
    CourseCatalog toDrop = null;
    for (CourseCatalog c : registeredCourses) {
        if (c.getCourseId().equalsIgnoreCase(courseId) 
            && c.getSemester().equalsIgnoreCase(semester)) {
            toDrop = c;
            break;
        }
    }

    if (toDrop != null) {
        registeredCourses.remove(toDrop);
        totalCredits -= toDrop.getCreditHours();
        fieldcredits.setText(String.valueOf(totalCredits));

        // Refresh registration table
        populateRegisterTable();

        JOptionPane.showMessageDialog(this, "Dropped course: " + toDrop.getCourseName());
    }
    if (gradPanel != null) gradPanel.refreshTable();
if (transcriptPanel != null) transcriptPanel.refreshTable();
if (financePanel != null) financePanel.refreshTable();
    }//GEN-LAST:event_btndropActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        searchCourses();
    }//GEN-LAST:event_btnsearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Courseofferingtable;
    private javax.swing.JTable Courseregistertable;
    private javax.swing.JButton btnEnroll;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btndrop;
    private javax.swing.JButton btnsearch;
    private javax.swing.JComboBox<String> combocourseid;
    private javax.swing.JComboBox<String> combosem;
    private javax.swing.JTextField fieldcredits;
    private javax.swing.JTextField fieldsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblcourseoffering;
    private javax.swing.JLabel lblcredits;
    private javax.swing.JLabel lblregistercourse;
    private javax.swing.JLabel lblsem;
    private javax.swing.JLabel lbltitle;
    // End of variables declaration//GEN-END:variables
}
