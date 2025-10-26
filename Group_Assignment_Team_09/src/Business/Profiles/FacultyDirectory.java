/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

/**
 *
 * @author aksha
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Business.Person.Person;
import java.util.ArrayList;

import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class FacultyDirectory {

    ArrayList<FacultyProfile> facultylist;

    public FacultyDirectory() {
        facultylist = new ArrayList();
    }

    public FacultyProfile newFacultyProfile(Person p) {
        FacultyProfile fp = new FacultyProfile(p);
        facultylist.add(fp);
        return fp;
    }

    public FacultyProfile findFaculty(String id) {
        for (FacultyProfile fp : facultylist) {
            if (fp.isMatch(id)) {
                return fp;
            }
        }
        return null; //not found after going through the whole list
    }

    /**
     * Search faculty by name (partial match, case-insensitive) This is Search
     * Method #1 for Admin
     */
    public ArrayList<FacultyProfile> searchByName(String searchTerm) {
        ArrayList<FacultyProfile> results = new ArrayList<>();

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return results;
        }

        String search = searchTerm.toLowerCase().trim();

        for (FacultyProfile fp : facultylist) {
            Person p = fp.getPerson();
            if (p.getName() != null && p.getName().toLowerCase().contains(search)) {
                results.add(fp);
            }
        }

        return results;
    }

    /**
     * Search faculty by University ID (exact match) This is Search Method #2
     * for Admin
     */
    public FacultyProfile searchByUniversityId(String univId) {
        if (univId == null || univId.trim().isEmpty()) {
            return null;
        }

        for (FacultyProfile fp : facultylist) {
            Person p = fp.getPerson();
            if (p.getUniversityId() != null && p.getUniversityId().equals(univId.trim())) {
                return fp;
            }
        }

        return null;
    }

    /**
     * Search faculty by department (partial match, case-insensitive) This is
     * Search Method #3 for Admin
     */
    public ArrayList<FacultyProfile> searchByDepartment(String dept) {
        ArrayList<FacultyProfile> results = new ArrayList<>();

        if (dept == null || dept.trim().isEmpty()) {
            return results;
        }

        String search = dept.toLowerCase().trim();

        for (FacultyProfile fp : facultylist) {
            if (fp.getDepartment() != null && fp.getDepartment().toLowerCase().contains(search)) {
                results.add(fp);
            }
        }

        return results;
    }

    /**
     * Get all faculty
     */
    public ArrayList<FacultyProfile> getFacultyList() {
        return facultylist;
    }

    /**
     * Delete faculty profile
     */
    public boolean deleteFaculty(FacultyProfile fp) {
        return facultylist.remove(fp);
    }
}
