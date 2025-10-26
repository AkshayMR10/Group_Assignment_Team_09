/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class PersonDirectory {

    ArrayList<Person> personlist;

    public PersonDirectory() {

        personlist = new ArrayList();

    }

    public Person newPerson(String id) {

        Person p = new Person(id);
        personlist.add(p);
        return p;
    }

    public Person findPerson(String id) {

        for (Person p : personlist) {

            if (p.isMatch(id)) {
                return p;
            }
        }
        return null; //not found after going through the whole list
    }

    public Person findPersonByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        for (Person p : personlist) {
            if (p.getEmail() != null && p.getEmail().equalsIgnoreCase(email.trim())) {
                return p;
            }
        }
        return null;
    }

    public Person findPersonByUniversityId(String univId) {
        if (univId == null || univId.trim().isEmpty()) {
            return null;
        }

        for (Person p : personlist) {
            if (p.getUniversityId() != null && p.getUniversityId().equals(univId.trim())) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Person> searchByName(String searchTerm) {
        ArrayList<Person> results = new ArrayList<>();

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return results;
        }

        String search = searchTerm.toLowerCase().trim();

        for (Person p : personlist) {
            if (p.getName() != null && p.getName().toLowerCase().contains(search)) {
                results.add(p);
            }
        }

        return results;
    }

    public Person searchById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        // Try person ID first
        Person p = findPerson(id);
        if (p != null) {
            return p;
        }

        // Try university ID
        return findPersonByUniversityId(id);
    }

    public boolean isDuplicateEmail(String email) {
        return findPersonByEmail(email) != null;
    }

    /**
     * Check if university ID already exists (for duplicate prevention)
     */
    public boolean isDuplicateUniversityId(String univId) {
        return findPersonByUniversityId(univId) != null;
    }

    /**
     * Generate unique University ID Format: U00001, U00002, etc.
     */
    public String generateUniversityId() {
        int nextNumber = personlist.size() + 1;

        // Keep incrementing until we find a unique ID
        String univId;
        do {
            univId = String.format("U%05d", nextNumber);
            nextNumber++;
        } while (isDuplicateUniversityId(univId));

        return univId;
    }

    /**
     * Get all persons
     */
    public ArrayList<Person> getPersonList() {
        return personlist;
    }

}
