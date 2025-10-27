/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */


import Business.Business;
import Business.CourseCatalog.Course;
import Business.CourseCatalog.CourseCatalog;
import Business.CourseSchedule.CourseLoad;
import Business.CourseSchedule.CourseOffer;
import Business.CourseSchedule.CourseSchedule;
import Business.Degree.Degree;
import Business.Person.Person;
import Business.Person.PersonDirectory;

import Business.Profiles.EmployeeDirectory;
import Business.Profiles.EmployeeProfile;

import Business.Profiles.StudentDirectory;
import Business.Profiles.StudentProfile;

import Business.Profiles.FacultyDirectory;
import Business.Profiles.FacultyProfile;

import Business.Profiles.RegistrarDirectory;
import Business.Profiles.RegistrarProfile;

import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class ConfigureABusiness {

    static Business initialize() {
        Business business = new Business("Northeastern University - Information Systems");

        // Create Persons with FULL details
        PersonDirectory persondirectory = business.getPersonDirectory();

        // ========== ADMIN (Employee) ==========
        Person person001 = persondirectory.newPerson("001");
        person001.setUniversityId("U00001");
        person001.setName("John Smith");
        person001.setEmail("john.smith@northeastern.edu");
        person001.setPhone("617-123-4567");
        person001.setAddress("360 Huntington Ave, Boston, MA");

        Person person002 = persondirectory.newPerson("002");
        person002.setUniversityId("U00002");
        person002.setName("Gina Montana");
        person002.setEmail("gina.montana@northeastern.edu");
        person002.setPhone("617-123-4568");
        person002.setAddress("360 Huntington Ave, Boston, MA");

        // ========== STUDENTS (10 students as required) ==========
        Person person003 = persondirectory.newPerson("003");
        person003.setUniversityId("U00003");
        person003.setName("Adam Rollen");
        person003.setEmail("adam.rollen@northeastern.edu");
        person003.setPhone("617-123-4569");
        person003.setAddress("123 Student St, Boston, MA");

        Person person004 = persondirectory.newPerson("004");
        person004.setUniversityId("U00004");
        person004.setName("Emily Chen");
        person004.setEmail("emily.chen@northeastern.edu");
        person004.setPhone("617-123-4570");
        person004.setAddress("456 Campus Dr, Boston, MA");

        Person person005 = persondirectory.newPerson("005");
        person005.setUniversityId("U00005");
        person005.setName("Jim Dellon");
        person005.setEmail("jim.dellon@northeastern.edu");
        person005.setPhone("617-123-4571");
        person005.setAddress("789 College Ave, Boston, MA");

        Person person006 = persondirectory.newPerson("006");
        person006.setUniversityId("U00006");
        person006.setName("Anna Shnider");
        person006.setEmail("anna.shnider@northeastern.edu");
        person006.setPhone("617-123-4572");
        person006.setAddress("321 University Blvd, Boston, MA");

        Person person007 = persondirectory.newPerson("007");
        person007.setUniversityId("U00007");
        person007.setName("Laura Brown");
        person007.setEmail("laura.brown@northeastern.edu");
        person007.setPhone("617-123-4573");
        person007.setAddress("654 Grad Hall, Boston, MA");

        Person person008 = persondirectory.newPerson("008");
        person008.setUniversityId("U00008");
        person008.setName("Jack While");
        person008.setEmail("jack.while@northeastern.edu");
        person008.setPhone("617-123-4574");
        person008.setAddress("987 Residence Hall, Boston, MA");

        Person person009 = persondirectory.newPerson("009");
        person009.setUniversityId("U00009");
        person009.setName("Sarah Johnson");
        person009.setEmail("sarah.johnson@northeastern.edu");
        person009.setPhone("617-123-4575");
        person009.setAddress("147 Dorm Circle, Boston, MA");

        Person person010 = persondirectory.newPerson("010");
        person010.setUniversityId("U00010");
        person010.setName("Michael Lee");
        person010.setEmail("michael.lee@northeastern.edu");
        person010.setPhone("617-123-4576");
        person010.setAddress("258 Student Way, Boston, MA");

        Person person011 = persondirectory.newPerson("011");
        person011.setUniversityId("U00011");
        person011.setName("Jessica Taylor");
        person011.setEmail("jessica.taylor@northeastern.edu");
        person011.setPhone("617-123-4577");
        person011.setAddress("369 Campus Lane, Boston, MA");

        Person person012 = persondirectory.newPerson("012");
        person012.setUniversityId("U00012");
        person012.setName("David Martinez");
        person012.setEmail("david.martinez@northeastern.edu");
        person012.setPhone("617-123-4578");
        person012.setAddress("741 College Rd, Boston, MA");

        // ========== FACULTY (10 faculty as required) ==========
        Person person013 = persondirectory.newPerson("013");
        person013.setUniversityId("U00013");
        person013.setName("Monac Livis");
        person013.setEmail("mocac.livis@northeastern.edu");
        person013.setPhone("617-373-2000");
        person013.setAddress("360 Huntington Ave, Boston, MA");

        Person person014 = persondirectory.newPerson("014");
        person014.setUniversityId("U00014");
        person014.setName("Prof. Amanda White");
        person014.setEmail("amanda.white@northeastern.edu");
        person014.setPhone("617-373-2001");
        person014.setAddress("360 Huntington Ave, Boston, MA");

        Person person015 = persondirectory.newPerson("015");
        person015.setUniversityId("U00015");
        person015.setName("Dr. Robert Clark");
        person015.setEmail("robert.clark@northeastern.edu");
        person015.setPhone("617-373-2002");
        person015.setAddress("360 Huntington Ave, Boston, MA");

        Person person016 = persondirectory.newPerson("016");
        person016.setUniversityId("U00016");
        person016.setName("Prof. Lisa Anderson");
        person016.setEmail("lisa.anderson@northeastern.edu");
        person016.setPhone("617-373-2003");
        person016.setAddress("360 Huntington Ave, Boston, MA");

        Person person017 = persondirectory.newPerson("017");
        person017.setUniversityId("U00017");
        person017.setName("Dr. James Wilson");
        person017.setEmail("james.wilson@northeastern.edu");
        person017.setPhone("617-373-2004");
        person017.setAddress("360 Huntington Ave, Boston, MA");

        Person person018 = persondirectory.newPerson("018");
        person018.setUniversityId("U00018");
        person018.setName("Prof. Maria Garcia");
        person018.setEmail("maria.garcia@northeastern.edu");
        person018.setPhone("617-373-2005");
        person018.setAddress("360 Huntington Ave, Boston, MA");

        Person person019 = persondirectory.newPerson("019");
        person019.setUniversityId("U00019");
        person019.setName("Dr. Thomas Brown");
        person019.setEmail("thomas.brown@northeastern.edu");
        person019.setPhone("617-373-2006");
        person019.setAddress("360 Huntington Ave, Boston, MA");

        Person person020 = persondirectory.newPerson("020");
        person020.setUniversityId("U00020");
        person020.setName("Prof. Jennifer Davis");
        person020.setEmail("jennifer.davis@northeastern.edu");
        person020.setPhone("617-373-2007");
        person020.setAddress("360 Huntington Ave, Boston, MA");

        Person person021 = persondirectory.newPerson("021");
        person021.setUniversityId("U00021");
        person021.setName("Dr. William Miller");
        person021.setEmail("william.miller@northeastern.edu");
        person021.setPhone("617-373-2008");
        person021.setAddress("360 Huntington Ave, Boston, MA");

        Person person022 = persondirectory.newPerson("022");
        person022.setUniversityId("U00022");
        person022.setName("Prof. Patricia Moore");
        person022.setEmail("patricia.moore@northeastern.edu");
        person022.setPhone("617-373-2009");
        person022.setAddress("360 Huntington Ave, Boston, MA");

        // ========== REGISTRAR (1 registrar as required) ==========
        Person person023 = persondirectory.newPerson("023");
        person023.setUniversityId("U00023");
        person023.setName("Susan Williams");
        person023.setEmail("susan.williams@northeastern.edu");
        person023.setPhone("617-373-3000");
        person023.setAddress("360 Huntington Ave, Boston, MA");

        // Additional persons
        Person person024 = persondirectory.newPerson("024");
        person024.setUniversityId("U00024");
        person024.setName("Christopher Davis");
        person024.setEmail("christopher.davis@northeastern.edu");
        person024.setPhone("617-373-2010");
        person024.setAddress("360 Huntington Ave, Boston, MA");

        Person person025 = persondirectory.newPerson("025");
        person025.setUniversityId("U00025");
        person025.setName("Michelle Rodriguez");
        person025.setEmail("michelle.rodriguez@northeastern.edu");
        person025.setPhone("617-373-2011");
        person025.setAddress("360 Huntington Ave, Boston, MA");

        Person person026 = persondirectory.newPerson("026");
        person026.setUniversityId("U00026");
        person026.setName("Kevin Thompson");
        person026.setEmail("kevin.thompson@northeastern.edu");
        person026.setPhone("617-373-2012");
        person026.setAddress("360 Huntington Ave, Boston, MA");

        Person person027 = persondirectory.newPerson("027");
        person027.setUniversityId("U00027");
        person027.setName("Rachel Green");
        person027.setEmail("rachel.green@northeastern.edu");
        person027.setPhone("617-373-2013");
        person027.setAddress("360 Huntington Ave, Boston, MA");

        Person person028 = persondirectory.newPerson("028");
        person028.setUniversityId("U00028");
        person028.setName("Brandon Hill");
        person028.setEmail("brandon.hill@northeastern.edu");
        person028.setPhone("617-373-2014");
        person028.setAddress("360 Huntington Ave, Boston, MA");

        Person person029 = persondirectory.newPerson("029");
        person029.setUniversityId("U00029");
        person029.setName("Samantha King");
        person029.setEmail("samantha.king@northeastern.edu");
        person029.setPhone("617-373-2015");
        person029.setAddress("360 Huntington Ave, Boston, MA");

        Person person030 = persondirectory.newPerson("030");
        person030.setUniversityId("U00030");
        person030.setName("Daniel Wright");
        person030.setEmail("daniel.wright@northeastern.edu");
        person030.setPhone("617-373-2016");
        person030.setAddress("360 Huntington Ave, Boston, MA");

        // ========== CREATE PROFILES ==========
        // Create Admin Profiles
        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
        EmployeeProfile employeeprofile0 = employeedirectory.newEmployeeProfile(person001);
        EmployeeProfile employeeprofile1 = employeedirectory.newEmployeeProfile(person002);

        // Additional Admins
        EmployeeProfile employeeprofile2 = employeedirectory.newEmployeeProfile(person027);
        EmployeeProfile employeeprofile3 = employeedirectory.newEmployeeProfile(person028);

        // Create Student Profiles
        StudentDirectory studentdirectory = business.getStudentDirectory();

        StudentProfile studentprofile0 = studentdirectory.newStudentProfile(person003);
        studentprofile0.setDepartment("Information Systems");
        studentprofile0.setProgram("MSIS");
        studentprofile0.setEnrollmentDate("2024-09-01");

        StudentProfile studentprofile1 = studentdirectory.newStudentProfile(person004);
        studentprofile1.setDepartment("Information Systems");
        studentprofile1.setProgram("MSIS");
        studentprofile1.setEnrollmentDate("2024-09-01");

        StudentProfile studentprofile2 = studentdirectory.newStudentProfile(person005);
        studentprofile2.setDepartment("Information Systems");
        studentprofile2.setProgram("MSIS");
        studentprofile2.setEnrollmentDate("2024-01-15");

        StudentProfile studentprofile3 = studentdirectory.newStudentProfile(person006);
        studentprofile3.setDepartment("Information Systems");
        studentprofile3.setProgram("MSIS");
        studentprofile3.setEnrollmentDate("2024-01-15");

        StudentProfile studentprofile4 = studentdirectory.newStudentProfile(person007);
        studentprofile4.setDepartment("Computer Science");
        studentprofile4.setProgram("MS CS");
        studentprofile4.setEnrollmentDate("2024-09-01");

        StudentProfile studentprofile5 = studentdirectory.newStudentProfile(person008);
        studentprofile5.setDepartment("Computer Science");
        studentprofile5.setProgram("MS CS");
        studentprofile5.setEnrollmentDate("2024-09-01");

        StudentProfile studentprofile6 = studentdirectory.newStudentProfile(person009);
        studentprofile6.setDepartment("Information Systems");
        studentprofile6.setProgram("MSIS");
        studentprofile6.setEnrollmentDate("2023-09-01");

        StudentProfile studentprofile7 = studentdirectory.newStudentProfile(person010);
        studentprofile7.setDepartment("Computer Science");
        studentprofile7.setProgram("MS CS");
        studentprofile7.setEnrollmentDate("2023-09-01");

        StudentProfile studentprofile8 = studentdirectory.newStudentProfile(person011);
        studentprofile8.setDepartment("Information Systems");
        studentprofile8.setProgram("MSIS");
        studentprofile8.setEnrollmentDate("2023-01-15");

        StudentProfile studentprofile9 = studentdirectory.newStudentProfile(person012);
        studentprofile9.setDepartment("Computer Science");
        studentprofile9.setProgram("MS CS");
        studentprofile9.setEnrollmentDate("2023-01-15");

        StudentProfile studentprofile10 = studentdirectory.newStudentProfile(person024);
        studentprofile10.setDepartment("Information Systems");
        studentprofile10.setProgram("MSIS");
        studentprofile10.setEnrollmentDate("2024-09-01");

        StudentProfile studentprofile11 = studentdirectory.newStudentProfile(person025);
        studentprofile11.setDepartment("Computer Science");
        studentprofile11.setProgram("MS CS");
        studentprofile11.setEnrollmentDate("2024-01-15");

        // Create Faculty Profiles
        FacultyDirectory facultydirectory = business.getFacultyDirectory();

        FacultyProfile facultyprofile0 = facultydirectory.newFacultyProfile(person013);
        facultyprofile0.setDepartment("Information Systems");
        facultyprofile0.setOffice("Room 301, West Village H");
        facultyprofile0.setOfficeHours("Mon/Wed 2-4 PM");
        facultyprofile0.setHireDate("2010-09-01");
        facultyprofile0.setSpecialization("Software Engineering");

        FacultyProfile facultyprofile1 = facultydirectory.newFacultyProfile(person014);
        facultyprofile1.setDepartment("Information Systems");
        facultyprofile1.setOffice("Room 302, West Village H");
        facultyprofile1.setOfficeHours("Tue/Thu 10-12 PM");
        facultyprofile1.setHireDate("2012-01-15");
        facultyprofile1.setSpecialization("Data Science");

        FacultyProfile facultyprofile2 = facultydirectory.newFacultyProfile(person015);
        facultyprofile2.setDepartment("Information Systems");
        facultyprofile2.setOffice("Room 303, West Village H");
        facultyprofile2.setOfficeHours("Mon/Wed 10-12 PM");
        facultyprofile2.setHireDate("2011-08-20");
        facultyprofile2.setSpecialization("Database Systems");

        FacultyProfile facultyprofile3 = facultydirectory.newFacultyProfile(person016);
        facultyprofile3.setDepartment("Information Systems");
        facultyprofile3.setOffice("Room 304, West Village H");
        facultyprofile3.setOfficeHours("Tue/Thu 2-4 PM");
        facultyprofile3.setHireDate("2013-09-01");
        facultyprofile3.setSpecialization("Web Development");

        FacultyProfile facultyprofile4 = facultydirectory.newFacultyProfile(person017);
        facultyprofile4.setDepartment("Computer Science");
        facultyprofile4.setOffice("Room 401, West Village H");
        facultyprofile4.setOfficeHours("Mon/Wed 3-5 PM");
        facultyprofile4.setHireDate("2009-01-10");
        facultyprofile4.setSpecialization("Machine Learning");

        FacultyProfile facultyprofile5 = facultydirectory.newFacultyProfile(person018);
        facultyprofile5.setDepartment("Computer Science");
        facultyprofile5.setOffice("Room 402, West Village H");
        facultyprofile5.setOfficeHours("Tue/Thu 1-3 PM");
        facultyprofile5.setHireDate("2014-09-01");
        facultyprofile5.setSpecialization("Cybersecurity");

        FacultyProfile facultyprofile6 = facultydirectory.newFacultyProfile(person019);
        facultyprofile6.setDepartment("Computer Science");
        facultyprofile6.setOffice("Room 403, West Village H");
        facultyprofile6.setOfficeHours("Mon/Wed 1-3 PM");
        facultyprofile6.setHireDate("2010-01-15");
        facultyprofile6.setSpecialization("Algorithms");

        FacultyProfile facultyprofile7 = facultydirectory.newFacultyProfile(person020);
        facultyprofile7.setDepartment("Information Systems");
        facultyprofile7.setOffice("Room 305, West Village H");
        facultyprofile7.setOfficeHours("Tue/Thu 11-1 PM");
        facultyprofile7.setHireDate("2015-09-01");
        facultyprofile7.setSpecialization("UI/UX Design");

        FacultyProfile facultyprofile8 = facultydirectory.newFacultyProfile(person021);
        facultyprofile8.setDepartment("Computer Science");
        facultyprofile8.setOffice("Room 404, West Village H");
        facultyprofile8.setOfficeHours("Mon/Wed 11-1 PM");
        facultyprofile8.setHireDate("2008-09-01");
        facultyprofile8.setSpecialization("Operating Systems");

        FacultyProfile facultyprofile9 = facultydirectory.newFacultyProfile(person022);
        facultyprofile9.setDepartment("Information Systems");
        facultyprofile9.setOffice("Room 306, West Village H");
        facultyprofile9.setOfficeHours("Tue/Thu 3-5 PM");
        facultyprofile9.setHireDate("2016-01-10");
        facultyprofile9.setSpecialization("Cloud Computing");

        FacultyProfile facultyprofile10 = facultydirectory.newFacultyProfile(person026);
        facultyprofile10.setDepartment("Information Systems");
        facultyprofile10.setOffice("Room 307, West Village H");
        facultyprofile10.setOfficeHours("Mon/Wed 9-11 AM");
        facultyprofile10.setHireDate("2017-09-01");
        facultyprofile10.setSpecialization("Mobile Development");

        // Create Registrar Profile
        RegistrarDirectory registrardirectory = business.getRegistrarDirectory();

        RegistrarProfile registrarprofile0 = registrardirectory.newRegistrarProfile(person023);
        registrarprofile0.setDepartment("Registrar Office");
        registrarprofile0.setOffice("Room 100, Administrative Building");
        registrarprofile0.setOfficeHours("Mon-Fri 9 AM - 5 PM");
        registrarprofile0.setAssignedSemester("Fall 2025");

        RegistrarProfile registrarprofile1 = registrardirectory.newRegistrarProfile(person029);
        registrarprofile1.setDepartment("Registrar Office");
        registrarprofile1.setOffice("Room 101, Administrative Building");
        registrarprofile1.setOfficeHours("Mon-Fri 9 AM - 5 PM");

        // ========== CREATE USER ACCOUNTS ==========
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();

        // Admin accounts
        UserAccount ua1 = uadirectory.newUserAccount(employeeprofile0, "admin", "****");
        UserAccount ua2 = uadirectory.newUserAccount(employeeprofile1, "gina", "****");

        // Student accounts
        UserAccount ua3 = uadirectory.newUserAccount(studentprofile0, "adam", "****");
        UserAccount ua4 = uadirectory.newUserAccount(studentprofile1, "emily", "****");
        UserAccount ua5 = uadirectory.newUserAccount(studentprofile2, "jim", "****");
        UserAccount ua6 = uadirectory.newUserAccount(studentprofile3, "anna", "****");
        UserAccount ua7 = uadirectory.newUserAccount(studentprofile4, "laura", "****");
        UserAccount ua8 = uadirectory.newUserAccount(studentprofile5, "jack", "****");
        UserAccount ua9 = uadirectory.newUserAccount(studentprofile6, "sarah", "****");
        UserAccount ua10 = uadirectory.newUserAccount(studentprofile7, "michael", "****");
        UserAccount ua11 = uadirectory.newUserAccount(studentprofile8, "jessica", "****");
        UserAccount ua12 = uadirectory.newUserAccount(studentprofile9, "david", "****");

        // Faculty accounts
        UserAccount ua13 = uadirectory.newUserAccount(facultyprofile0, "monac", "****");
        UserAccount ua14 = uadirectory.newUserAccount(facultyprofile1, "amanda", "****");
        UserAccount ua15 = uadirectory.newUserAccount(facultyprofile2, "robert", "****");
        UserAccount ua16 = uadirectory.newUserAccount(facultyprofile3, "lisa", "****");
        UserAccount ua17 = uadirectory.newUserAccount(facultyprofile4, "james", "****");
        UserAccount ua18 = uadirectory.newUserAccount(facultyprofile5, "maria", "****");
        UserAccount ua19 = uadirectory.newUserAccount(facultyprofile6, "thomas", "****");
        UserAccount ua20 = uadirectory.newUserAccount(facultyprofile7, "jennifer", "****");
        UserAccount ua21 = uadirectory.newUserAccount(facultyprofile8, "william", "****");
        UserAccount ua22 = uadirectory.newUserAccount(facultyprofile9, "patricia", "****");

        // Registrar account
        UserAccount ua23 = uadirectory.newUserAccount(registrarprofile0, "anu", "****");

        //Additional accounts
        UserAccount ua24 = uadirectory.newUserAccount(studentprofile10, "chris", "****");
        UserAccount ua25 = uadirectory.newUserAccount(studentprofile11, "michelle", "****");
        UserAccount ua26 = uadirectory.newUserAccount(facultyprofile10, "kevin", "****");
        UserAccount ua27 = uadirectory.newUserAccount(employeeprofile2, "rachel", "****");
        UserAccount ua28 = uadirectory.newUserAccount(employeeprofile3, "brandon", "****");
        UserAccount ua29 = uadirectory.newUserAccount(registrarprofile1, "samantha", "****");

        // ========== CREATE COURSES AND SCHEDULES ==========
        // Create Course Catalog
        CourseCatalog courseCatalog = business.getCourseCatalog();

        Course course1 = courseCatalog.newCourse("Application Engineering and Development", "INFO 5100", 4);
        Course course2 = courseCatalog.newCourse("Web Design and User Experience", "INFO 6150", 4);
        Course course3 = courseCatalog.newCourse("Program Structure and Algorithms", "INFO 6205", 4);
        Course course4 = courseCatalog.newCourse("Advances in Data Sciences", "INFO 7390", 4);
        Course course5 = courseCatalog.newCourse("Information Visualization", "INFO 7245", 4);
        Course course6 = courseCatalog.newCourse("Object Oriented Design", "CSYE 6200", 4);
        Course course7 = courseCatalog.newCourse("Database Management Systems", "DAMG 6210", 4);

        // Add core and elective courses to MSIS degree
        Degree degree = business.getDegree();
        degree.addCoreCourse(course1); // INFO 5100 is core
        degree.addElectiveCourse(course2);
        degree.addElectiveCourse(course3);
        degree.addElectiveCourse(course4);
        degree.addElectiveCourse(course5);
        degree.addElectiveCourse(course6);
        degree.addElectiveCourse(course7);

        /*System.out.println("MSIS Degree: " + degree.getCoreList().size() + " core courses, "
                + degree.getElectives().size() + " electives");*/
        // ========== CREATE FALL 2025 SCHEDULE ==========
        CourseSchedule fall2025 = business.newCourseSchedule("Fall 2025");

        CourseOffer co1 = fall2025.newCourseOffer("INFO 5100");
        co1.generatSeats(50);
        co1.AssignAsTeacher(facultyprofile0);

        CourseOffer co2 = fall2025.newCourseOffer("INFO 6150");
        co2.generatSeats(40);
        co2.AssignAsTeacher(facultyprofile1);

        CourseOffer co3 = fall2025.newCourseOffer("INFO 6205");
        co3.generatSeats(45);
        co3.AssignAsTeacher(facultyprofile2);
        //System.out.println("Created: INFO 6205 (45 seats) - " + facultyprofile2.getPerson().getName());

        CourseOffer co4 = fall2025.newCourseOffer("INFO 7390");
        co4.generatSeats(35);
        co4.AssignAsTeacher(facultyprofile3);

        CourseOffer co5 = fall2025.newCourseOffer("INFO 7245");
        co5.generatSeats(30);
        co5.AssignAsTeacher(facultyprofile4);

        // ========== CREATE SPRING 2025 SCHEDULE ==========
        CourseSchedule spring2025 = business.newCourseSchedule("Spring 2025");

        CourseOffer co6 = spring2025.newCourseOffer("CSYE 6200");
        co6.generatSeats(40);
        co6.AssignAsTeacher(facultyprofile5);

        CourseOffer co7 = spring2025.newCourseOffer("DAMG 6210");
        co7.generatSeats(45);
        co7.AssignAsTeacher(facultyprofile6);

        // ========== ENROLL STUDENTS IN COURSES ==========
        // Student 1: Adam - Fall 2025 (2 courses)
        CourseLoad cl1 = studentprofile0.newCourseLoad("Fall 2025");
        co1.assignEmptySeat(cl1); // Adam enrolls in INFO 5100
        co2.assignEmptySeat(cl1); // Adam enrolls in INFO 6150

        // Student 2: Emily - Fall 2025 (2 courses)
        CourseLoad cl2 = studentprofile1.newCourseLoad("Fall 2025");
        co1.assignEmptySeat(cl2); // Emily enrolls in INFO 5100
        co3.assignEmptySeat(cl2); // Emily enrolls in INFO 6205

        // Student 3: Jim - Fall 2025 (3 courses)
        CourseLoad cl3 = studentprofile2.newCourseLoad("Fall 2025");
        co1.assignEmptySeat(cl3); // Jim enrolls in INFO 5100
        co2.assignEmptySeat(cl3); // Jim enrolls in INFO 6150
        co3.assignEmptySeat(cl3); // Jim enrolls in INFO 6205

        // Student 4: Anna - Fall 2025 (2 courses)
        CourseLoad cl4 = studentprofile3.newCourseLoad("Fall 2025");
        co2.assignEmptySeat(cl4); // Anna enrolls in INFO 6150
        co4.assignEmptySeat(cl4); // Anna enrolls in INFO 7390
        //System.out.println(studentprofile3.getPerson().getName() + " enrolled in 2 courses (Fall 2025)");

        // Student 5: Laura - Fall 2025 (2 courses)
        CourseLoad cl5 = studentprofile4.newCourseLoad("Fall 2025");
        co1.assignEmptySeat(cl5); // Laura enrolls in INFO 5100
        co4.assignEmptySeat(cl5); // Laura enrolls in INFO 7390

        // Student 6: Jack - Spring 2025 (2 courses)
        CourseLoad cl6 = studentprofile5.newCourseLoad("Spring 2025");
        co6.assignEmptySeat(cl6); // Jack enrolls in CSYE 6200
        co7.assignEmptySeat(cl6); // Jack enrolls in DAMG 6210

        // Student 7: Sarah - Spring 2025 (2 courses)
        CourseLoad cl7 = studentprofile6.newCourseLoad("Spring 2025");
        co6.assignEmptySeat(cl7); // Sarah enrolls in CSYE 6200
        co7.assignEmptySeat(cl7); // Sarah enrolls in DAMG 6210

        // Student 8: Michael - Fall 2025 (1 course)
        CourseLoad cl8 = studentprofile7.newCourseLoad("Fall 2025");
        co5.assignEmptySeat(cl8); // Michael enrolls in INFO 7245

        // Student 9: Jessica - Fall 2025 (2 courses)
        CourseLoad cl9 = studentprofile8.newCourseLoad("Fall 2025");
        co3.assignEmptySeat(cl9); // Jessica enrolls in INFO 6205
        co5.assignEmptySeat(cl9); // Jessica enrolls in INFO 7245

        // Student 10: David - Spring 2025 (1 course)
        CourseLoad cl10 = studentprofile9.newCourseLoad("Spring 2025");
        co7.assignEmptySeat(cl10); // David enrolls in DAMG 6210

        return business;
    }
}
