package com.example.schools;

import com.example.schools.db.DbUtil;
import com.example.schools.db.DbPopulator;
import com.example.schools.db.Queries;
// импортируем ChartUtils
import com.example.schools.chart.ChartUtils;

public class Main {
    public static void main(String[] args) throws Exception {
        // Инициализируем БД и загружаем CSV
        DbUtil.initDb();
        DbPopulator.populate();

        // Задачи 1–3
        System.out.println("=== Task 1: Avg Students ===");
        Queries.avgStudents()
               .forEach((county, avg) ->
                   System.out.printf("%s: %.2f%n", county, avg));

        System.out.println("\n=== Task 2: Avg Expenditure >10 ===");
        Queries.avgExpenditure()
               .forEach((county, avg) ->
                   System.out.printf("%s: %.2f%n", county, avg));

        System.out.println("\n=== Task 3: Top Math Scores ===");
        Queries.topMath()
               .forEach((range, info) ->
                   System.out.printf("%s → %s%n", range, info));

        System.out.println("\nGenerating charts...");
        ChartUtils.plotAvgStudents();
        System.out.println("  avg_students.png created");
        ChartUtils.plotAvgExpenditure();
        System.out.println("  avg_expenditure.png created");
    }
}
