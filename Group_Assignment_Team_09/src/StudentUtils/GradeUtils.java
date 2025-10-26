/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Amrin
 */

public class GradeUtils {
    private static final Map<String, Double> gradeToPoints = new HashMap<>();

    static {
        gradeToPoints.put("A", 4.0);
        gradeToPoints.put("A-", 3.7);
        gradeToPoints.put("B+", 3.3);
        gradeToPoints.put("B", 3.0);
        gradeToPoints.put("B-", 2.7);
        gradeToPoints.put("C+", 2.3);
        gradeToPoints.put("C", 2.0);
        gradeToPoints.put("C-", 1.7);
        gradeToPoints.put("F", 0.0);
    }

    // Convert letter grade to points
    public static double getPoints(String grade) {
        return gradeToPoints.getOrDefault(grade, 0.0);
    }

    // Convert float points to letter grade
    public static String getLetterGrade(float points) {
        if (points >= 3.85) return "A";
        if (points >= 3.5) return "A-";
        if (points >= 3.15) return "B+";
        if (points >= 2.85) return "B";
        if (points >= 2.5) return "B-";
        if (points >= 2.15) return "C+";
        if (points >= 1.85) return "C";
        if (points >= 1.5) return "C-";
        return "F";
    }
}