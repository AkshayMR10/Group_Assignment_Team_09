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

    public static double getPoints(String grade) {
        return gradeToPoints.getOrDefault(grade, 0.0);
    }
}