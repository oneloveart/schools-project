package com.example.schools.db;

import com.example.schools.csv.CsvLoader;
import com.example.schools.model.School;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbPopulator {
    public static void populate() throws Exception {
        List<School> schools = CsvLoader.load("Школы.csv");
        try (Connection conn = DbUtil.getConnection()) {
            conn.setAutoCommit(false);

            // Подготовленные запросы
            var insCounty = conn.prepareStatement(
                "INSERT OR IGNORE INTO County(name) VALUES(?)");
            var selCounty = conn.prepareStatement(
                "SELECT id FROM County WHERE name = ?");
            var insSchool = conn.prepareStatement(
               "INSERT INTO School(district,name,grades,students,teachers,county_id) VALUES(?,?,?,?,?,?)",
               PreparedStatement.RETURN_GENERATED_KEYS);
            var insMetrics = conn.prepareStatement(
               "INSERT INTO Metrics(school_id,calworks,lunch,computer,expenditure,income,english,read,math) VALUES(?,?,?,?,?,?,?,?,?)");

            Map<String,Integer> countyCache = new HashMap<>();
            for (School s : schools) {
                // County
                countyCache.computeIfAbsent(s.getCounty(), name -> {
                    try {
                        insCounty.setString(1, name);
                        insCounty.executeUpdate();
                        selCounty.setString(1, name);
                        try (ResultSet rs = selCounty.executeQuery()) {
                            if (rs.next()) return rs.getInt(1);
                        }
                    } catch (Exception e) { throw new RuntimeException(e); }
                    return null;
                });
                int cid = countyCache.get(s.getCounty());

                // School
                insSchool.setInt(1, s.getDistrict());
                insSchool.setString(2, s.getName());
                insSchool.setString(3, s.getGrades());
                insSchool.setInt(4, s.getStudents());
                insSchool.setDouble(5, s.getTeachers());
                insSchool.setInt(6, cid);
                insSchool.executeUpdate();
                int sid;
                try (ResultSet keys = insSchool.getGeneratedKeys()) {
                   keys.next();
                   sid = keys.getInt(1);
                }

                // Metrics
                insMetrics.setInt(1, sid);
                insMetrics.setDouble(2, s.getCalworks());
                insMetrics.setDouble(3, s.getLunch());
                insMetrics.setInt(4, s.getComputer());
                insMetrics.setDouble(5, s.getExpenditure());
                insMetrics.setDouble(6, s.getIncome());
                insMetrics.setDouble(7, s.getEnglish());
                insMetrics.setDouble(8, s.getRead());
                insMetrics.setDouble(9, s.getMath());
                insMetrics.executeUpdate();
            }
            conn.commit();
        }
        System.out.println("Данные загружены в БД");
    }
}
