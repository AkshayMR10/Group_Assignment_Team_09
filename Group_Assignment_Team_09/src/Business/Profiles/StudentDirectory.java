/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Profiles;

import Business.Person.Person;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class StudentDirectory {

    ArrayList<StudentProfile> studentlist;

    public StudentDirectory() {

        studentlist = new ArrayList();

    }

    public StudentProfile newStudentProfile(Person p) {

        StudentProfile sp = new StudentProfile(p);
        studentlist.add(sp);
        return sp;
    }

    public StudentProfile findStudent(String id) {

        for (StudentProfile sp : studentlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; //not found after going through the whole list
    }

    /**
     * Search students by name (partial match, case-insensitive) This is Search
     * Method #1 for Admin
     */
    public ArrayList<StudentProfile> searchByName(String searchTerm) {
        ArrayList<StudentProfile> results = new ArrayList<>();

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return results;
        }

        String search = searchTerm.toLowerCase().trim();

        for (StudentProfile sp : studentlist) {
            Person p = sp.getPerson();
            if (p.getName() != null && p.getName().toLowerCase().contains(search)) {
                results.add(sp);
            }
        }

        return results;
    }

    /**
     * Search students by University ID (exact match) This is Search Method #2
     * for Admin
     */
    public StudentProfile searchByUniversityId(String univId) {
        if (univId == null || univId.trim().isEmpty()) {
            return null;
        }

        for (StudentProfile sp : studentlist) {
            Person p = sp.getPerson();
            if (p.getUniversityId() != null && p.getUniversityId().equals(univId.trim())) {
                return sp;
            }
        }

        return null;
    }

    /**
     * Search students by department (partial match, case-insensitive) This is
     * Search Method #3 for Admin
     */
    public ArrayList<StudentProfile> searchByDepartment(String dept) {
        ArrayList<StudentProfile> results = new ArrayList<>();

        if (dept == null || dept.trim().isEmpty()) {
            return results;
        }

        String search = dept.toLowerCase().trim();

        for (StudentProfile sp : studentlist) {
            if (sp.getDepartment() != null && sp.getDepartment().toLowerCase().contains(search)) {
                results.add(sp);
            }
        }

        return results;
    }

    /**
     * Get all students
     */
    public ArrayList<StudentProfile> getStudentList() {
        return studentlist;
    }

    /**
     * Delete student profile
     */
    public boolean deleteStudent(StudentProfile sp) {
        return studentlist.remove(sp);
    }

}
