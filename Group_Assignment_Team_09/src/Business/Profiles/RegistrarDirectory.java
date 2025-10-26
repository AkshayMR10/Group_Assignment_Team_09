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

/**
 *
 * @author Akshay
 */
public class RegistrarDirectory {

    ArrayList<RegistrarProfile> registrarlist;

    public RegistrarDirectory() {
        registrarlist = new ArrayList();
    }

    public RegistrarProfile newRegistrarProfile(Person p) {
        RegistrarProfile rp = new RegistrarProfile(p);
        registrarlist.add(rp);
        return rp;
    }

    public RegistrarProfile findRegistrar(String id) {
        for (RegistrarProfile rp : registrarlist) {
            if (rp.isMatch(id)) {
                return rp;
            }
        }
        return null;
    }

    /**
     * Search registrar by name (partial match, case-insensitive)
     */
    public ArrayList<RegistrarProfile> searchByName(String searchTerm) {
        ArrayList<RegistrarProfile> results = new ArrayList<>();

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return results;
        }

        String search = searchTerm.toLowerCase().trim();

        for (RegistrarProfile rp : registrarlist) {
            Person p = rp.getPerson();
            if (p.getName() != null && p.getName().toLowerCase().contains(search)) {
                results.add(rp);
            }
        }

        return results;
    }

    /**
     * Search registrar by University ID (exact match)
     */
    public RegistrarProfile searchByUniversityId(String univId) {
        if (univId == null || univId.trim().isEmpty()) {
            return null;
        }

        for (RegistrarProfile rp : registrarlist) {
            Person p = rp.getPerson();
            if (p.getUniversityId() != null && p.getUniversityId().equals(univId.trim())) {
                return rp;
            }
        }

        return null;
    }

    /**
     * Search registrar by department (partial match, case-insensitive)
     */
    public ArrayList<RegistrarProfile> searchByDepartment(String dept) {
        ArrayList<RegistrarProfile> results = new ArrayList<>();

        if (dept == null || dept.trim().isEmpty()) {
            return results;
        }

        String search = dept.toLowerCase().trim();

        for (RegistrarProfile rp : registrarlist) {
            if (rp.getDepartment() != null && rp.getDepartment().toLowerCase().contains(search)) {
                results.add(rp);
            }
        }

        return results;
    }

    /**
     * Get all registrars
     */
    public ArrayList<RegistrarProfile> getRegistrarList() {
        return registrarlist;
    }

    /**
     * Delete registrar profile
     */
    public boolean deleteRegistrar(RegistrarProfile rp) {
        return registrarlist.remove(rp);
    }
}
