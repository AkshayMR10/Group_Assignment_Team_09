/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.CourseCatalog;

/**
 *
 * @author aksha
 */
import Business.Business;
import java.util.ArrayList;

public class CourseCatalog {

    Business business;
    String lastupdated;
    ArrayList<Course> courselist;

    public CourseCatalog(Business d) {
        courselist = new ArrayList();
        business = d;
    }

    public ArrayList<Course> getCourseList() {
        return courselist;
    }

    public Course newCourse(String n, String nm, int cr) {
        Course c = new Course(n, nm, cr);
        courselist.add(c);
        return c;
    }

    public Course getCourseByNumber(String n) {
        for (Course c : courselist) {
            if (c.getCourseNumber().equals(n)) {
                return c;
            }
        }
        return null;
    }
}
