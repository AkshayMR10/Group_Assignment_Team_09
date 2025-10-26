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

/**
 *
 * @author Akshay
 */
public class RegistrarProfile extends Profile {

    // Registrar-specific fields
    private String department;
    private String office;
    private String officeHours;
    private String assignedSemester;

    public RegistrarProfile(Person p) {
        super(p);
    }

    @Override
    public String getRole() {
        return "Registrar";
    }

    // Getters and Setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getAssignedSemester() {
        return assignedSemester;
    }

    public void setAssignedSemester(String assignedSemester) {
        this.assignedSemester = assignedSemester;
    }

    @Override
    public String toString() {
        return getPerson().getName() + " (Registrar - " + department + ")";
    }
}
