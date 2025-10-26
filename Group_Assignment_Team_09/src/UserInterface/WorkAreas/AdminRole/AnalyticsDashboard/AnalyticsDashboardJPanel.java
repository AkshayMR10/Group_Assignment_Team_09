/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.AdminRole.AnalyticsDashboard;

import Business.Business;
import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;
import Business.Profiles.StudentDirectory;
import Business.Profiles.StudentProfile;
import Business.Profiles.FacultyDirectory;
import Business.Profiles.FacultyProfile;
import Business.CourseSchedule.CourseSchedule;
import Business.CourseSchedule.CourseOffer;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

/**
 *
 * @author aksha
 */
public class AnalyticsDashboardJPanel extends javax.swing.JPanel {

    JPanel CardSequencePanel;
    Business business;

    /**
     * Creates new form AnalyticsDashboardJPanel1
     */
    public AnalyticsDashboardJPanel(Business bz, JPanel jp) {
        CardSequencePanel = jp;
        this.business = bz;
        initComponents();

        // Load all analytics
        loadUsersByRoleAnalytics();
        loadStudentsByDepartmentAnalytics();
        loadFacultyByDepartmentAnalytics();
        loadCoursesBySemesterAnalytics();
        loadEnrollmentByCourseAnalytics();
        loadSummaryStatistics();
    }

    private void loadUsersByRoleAnalytics() {
        // Clear table
        int rc = usersByRoleTable.getRowCount();
        for (int i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) usersByRoleTable.getModel()).removeRow(i);
        }

        // Count users by role
        HashMap<String, Integer> roleCounts = new HashMap<>();
        UserAccountDirectory uaDir = business.getUserAccountDirectory();

        for (UserAccount ua : uaDir.getUserAccountList()) {
            String role = ua.getRole();
            roleCounts.put(role, roleCounts.getOrDefault(role, 0) + 1);
        }

        // Add to table
        for (String role : roleCounts.keySet()) {
            Object[] row = new Object[2];
            row[0] = role;
            row[1] = roleCounts.get(role);
            ((DefaultTableModel) usersByRoleTable.getModel()).addRow(row);
        }

        // Add total row
        Object[] totalRow = new Object[2];
        totalRow[0] = "TOTAL";
        totalRow[1] = uaDir.getUserAccountList().size();
        ((DefaultTableModel) usersByRoleTable.getModel()).addRow(totalRow);
    }

    private void loadStudentsByDepartmentAnalytics() {
        // Clear table
        int rc = studentsByDeptTable.getRowCount();
        for (int i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) studentsByDeptTable.getModel()).removeRow(i);
        }

        // Count students by department
        HashMap<String, Integer> deptCounts = new HashMap<>();
        StudentDirectory studentDir = business.getStudentDirectory();

        for (StudentProfile sp : studentDir.getStudentList()) {
            String dept = sp.getDepartment();
            if (dept == null || dept.trim().isEmpty()) {
                dept = "Unassigned";
            }
            deptCounts.put(dept, deptCounts.getOrDefault(dept, 0) + 1);
        }

        // Add to table
        for (String dept : deptCounts.keySet()) {
            Object[] row = new Object[2];
            row[0] = dept;
            row[1] = deptCounts.get(dept);
            ((DefaultTableModel) studentsByDeptTable.getModel()).addRow(row);
        }

        // Add total row
        Object[] totalRow = new Object[2];
        totalRow[0] = "TOTAL";
        totalRow[1] = studentDir.getStudentList().size();
        ((DefaultTableModel) studentsByDeptTable.getModel()).addRow(totalRow);
    }

    private void loadFacultyByDepartmentAnalytics() {
        // Clear table
        int rc = facultyByDeptTable.getRowCount();
        for (int i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) facultyByDeptTable.getModel()).removeRow(i);
        }

        // Count faculty by department
        HashMap<String, Integer> deptCounts = new HashMap<>();
        FacultyDirectory facultyDir = business.getFacultyDirectory();

        for (FacultyProfile fp : facultyDir.getFacultyList()) {
            String dept = fp.getDepartment();
            if (dept == null || dept.trim().isEmpty()) {
                dept = "Unassigned";
            }
            deptCounts.put(dept, deptCounts.getOrDefault(dept, 0) + 1);
        }

        // Add to table
        for (String dept : deptCounts.keySet()) {
            Object[] row = new Object[2];
            row[0] = dept;
            row[1] = deptCounts.get(dept);
            ((DefaultTableModel) facultyByDeptTable.getModel()).addRow(row);
        }

        // Add total row
        Object[] totalRow = new Object[2];
        totalRow[0] = "TOTAL";
        totalRow[1] = facultyDir.getFacultyList().size();
        ((DefaultTableModel) facultyByDeptTable.getModel()).addRow(totalRow);
    }

    private void loadCoursesBySemesterAnalytics() {
        // Clear table
        int rc = coursesBySemesterTable.getRowCount();
        for (int i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) coursesBySemesterTable.getModel()).removeRow(i);
        }

        HashMap<String, CourseSchedule> schedules = business.getMasterCourseCatalog();

        int totalCourses = 0;
        for (String semester : schedules.keySet()) {
            CourseSchedule cs = schedules.get(semester);
            int courseCount = cs.getSchedule().size();

            Object[] row = new Object[2];
            row[0] = semester;
            row[1] = courseCount;
            ((DefaultTableModel) coursesBySemesterTable.getModel()).addRow(row);

            totalCourses += courseCount;
        }

        // Total row
        Object[] totalRow = new Object[2];
        totalRow[0] = "TOTAL OFFERS";
        totalRow[1] = totalCourses;
        ((DefaultTableModel) coursesBySemesterTable.getModel()).addRow(totalRow);
    }

    private void loadEnrollmentByCourseAnalytics() {
        // Clear table
        int rc = enrollmentByCourseTable.getRowCount();
        for (int i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) enrollmentByCourseTable.getModel()).removeRow(i);
        }

        HashMap<String, CourseSchedule> schedules = business.getMasterCourseCatalog();

        int totalEnrollments = 0;
        for (String semester : schedules.keySet()) {
            CourseSchedule cs = schedules.get(semester);

            for (CourseOffer co : cs.getSchedule()) {
                int enrolled = co.getEnrolledCount();

                Object[] row = new Object[4];
                row[0] = semester;
                row[1] = co.getCourseNumber();
                row[2] = co.getSubjectCourse().getName();
                row[3] = enrolled;
                ((DefaultTableModel) enrollmentByCourseTable.getModel()).addRow(row);

                totalEnrollments += enrolled;
            }
        }

        // Total row
        Object[] totalRow = new Object[4];
        totalRow[0] = "";
        totalRow[1] = "";
        totalRow[2] = "TOTAL ENROLLMENTS";
        totalRow[3] = totalEnrollments;
        ((DefaultTableModel) enrollmentByCourseTable.getModel()).addRow(totalRow);
    }

    private void loadSummaryStatistics() {
        // Calculate summary statistics
        int totalPersons = business.getPersonDirectory().getPersonList().size();
        int totalUsers = business.getUserAccountDirectory().getUserAccountList().size();
        int totalStudents = business.getStudentDirectory().getStudentList().size();
        int totalFaculty = business.getFacultyDirectory().getFacultyList().size();
        int totalAdmins = business.getEmployeeDirectory().getEmployeeList().size();
        int totalRegistrars = business.getRegistrarDirectory().getRegistrarList().size();

        // Course statistics
        int totalCourses = business.getCourseCatalog().getCourseList().size();
        int totalSemesters = business.getMasterCourseCatalog().size();

        // Calculate total enrollments
        int totalEnrollments = 0;
        for (CourseSchedule cs : business.getMasterCourseCatalog().values()) {
            for (CourseOffer co : cs.getSchedule()) {
                totalEnrollments += co.getEnrolledCount();
            }
        }

        // Calculate total revenue
        int totalRevenue = business.calculateTotalRevenues();

        // Update labels
        totalPersonsLabel.setText(String.valueOf(totalPersons));
        totalUsersLabel.setText(String.valueOf(totalUsers));
        totalStudentsLabel.setText(String.valueOf(totalStudents));
        totalFacultyLabel.setText(String.valueOf(totalFaculty));
        totalAdminsLabel.setText(String.valueOf(totalAdmins));
        totalRegistrarsLabel.setText(String.valueOf(totalRegistrars));
        totalCoursesLabel.setText(String.valueOf(totalCourses));
        totalSemestersLabel.setText(String.valueOf(totalSemesters));
        totalEnrollmentsLabel.setText(String.valueOf(totalEnrollments));
        totalRevenueLabel.setText("$" + String.format("%,d", totalRevenue));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersByRoleTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentsByDeptTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        facultyByDeptTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        coursesBySemesterTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        enrollmentByCourseTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        totalPersonsLabel = new javax.swing.JLabel();
        totalUsersLabel = new javax.swing.JLabel();
        totalFacultyLabel = new javax.swing.JLabel();
        totalStudentsLabel = new javax.swing.JLabel();
        totalAdminsLabel = new javax.swing.JLabel();
        totalRegistrarsLabel = new javax.swing.JLabel();
        totalCoursesLabel = new javax.swing.JLabel();
        totalSemestersLabel = new javax.swing.JLabel();
        totalEnrollmentsLabel = new javax.swing.JLabel();
        totalRevenueLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("University Analytics Dashboard");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Active Users by role");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Students by department");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Faculty by Department");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Courses Offered per Semester");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Student Enrollment by Course");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Summary Statistics");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Total Persons:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Total User Accounts:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Total Students:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Total Faculty:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Total Admins:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Total Registrars:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Total Courses:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Total Semesters:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Total Enrollments:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Tution Revenue:");

        usersByRoleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Role", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(usersByRoleTable);

        studentsByDeptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Department", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(studentsByDeptTable);

        facultyByDeptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Department", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(facultyByDeptTable);

        coursesBySemesterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Semester", "Courses"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(coursesBySemesterTable);

        enrollmentByCourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Semester", "Course ID", "Course Name", "Enrolled"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(enrollmentByCourseTable);

        backButton.setText("<<<Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(102, 204, 255));
        refreshButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setText("Refresh Data");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        totalPersonsLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalPersonsLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalUsersLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalUsersLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalFacultyLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalFacultyLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalStudentsLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalStudentsLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalAdminsLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalAdminsLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalRegistrarsLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalRegistrarsLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalCoursesLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalCoursesLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalSemestersLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalSemestersLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalEnrollmentsLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalEnrollmentsLabel.setForeground(new java.awt.Color(255, 255, 255));

        totalRevenueLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalRevenueLabel.setForeground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addGap(164, 164, 164)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(totalPersonsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalUsersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalFacultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalAdminsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalStudentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalRegistrarsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalCoursesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalSemestersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalEnrollmentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalRevenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)
                        .addGap(113, 113, 113))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(totalPersonsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9))
                                    .addComponent(totalUsersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(totalStudentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(totalFacultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12))
                                    .addComponent(totalAdminsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(totalRegistrarsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)))
                    .addComponent(totalCoursesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(totalSemestersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(totalEnrollmentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(totalRevenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).show(CardSequencePanel, "Admin");
    }//GEN-LAST:event_backButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        loadUsersByRoleAnalytics();
        loadStudentsByDepartmentAnalytics();
        loadFacultyByDepartmentAnalytics();
        loadCoursesBySemesterAnalytics();
        loadEnrollmentByCourseAnalytics();
        loadSummaryStatistics();
    }//GEN-LAST:event_refreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTable coursesBySemesterTable;
    private javax.swing.JTable enrollmentByCourseTable;
    private javax.swing.JTable facultyByDeptTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable studentsByDeptTable;
    private javax.swing.JLabel totalAdminsLabel;
    private javax.swing.JLabel totalCoursesLabel;
    private javax.swing.JLabel totalEnrollmentsLabel;
    private javax.swing.JLabel totalFacultyLabel;
    private javax.swing.JLabel totalPersonsLabel;
    private javax.swing.JLabel totalRegistrarsLabel;
    private javax.swing.JLabel totalRevenueLabel;
    private javax.swing.JLabel totalSemestersLabel;
    private javax.swing.JLabel totalStudentsLabel;
    private javax.swing.JLabel totalUsersLabel;
    private javax.swing.JTable usersByRoleTable;
    // End of variables declaration//GEN-END:variables
}
