package com.example.schools.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import com.example.schools.db.Queries;

import java.io.File;

public class ChartUtils {
    public static void plotAvgStudents() throws Exception {
        var data = Queries.avgStudents();
        var ds = new DefaultCategoryDataset();
        data.forEach((c,v)-> ds.addValue(v, "Students", c));

        JFreeChart barChart = ChartFactory.createBarChart(
            "Среднее число студентов", "County", "Avg Students", ds);

        // Явно используем утилиту из JFreeChart
        org.jfree.chart.ChartUtils.saveChartAsPNG(
            new File("avg_students.png"), barChart, 800, 600);
    }

    public static void plotAvgExpenditure() throws Exception {
        var data = Queries.avgExpenditure();
        var ds = new DefaultCategoryDataset();
        data.forEach((c,v)-> ds.addValue(v, "Expenditure", c));

        JFreeChart barChart = ChartFactory.createBarChart(
            "Средние расходы", "County", "Avg Expenditure", ds);

        org.jfree.chart.ChartUtils.saveChartAsPNG(
            new File("avg_expenditure.png"), barChart, 800, 600);
    }

    public static void main(String[] args) throws Exception {
        plotAvgStudents();
        plotAvgExpenditure();
    }
}
