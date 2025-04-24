package com.example.schools.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class Queries {
    // Задача 1
    public static Map<String, Double> avgStudents() throws Exception {
        String[] counties = {
         "Alameda","Butte","Fresno","Contra Costa","El Dorado",
         "Glenn","Los Angeles","Orange","San Diego","Santa Clara"
        };
        String inClause = String.join(",", java.util.Collections.nCopies(counties.length, "?"));
        String sql = "SELECT C.name, AVG(S.students) AS avg_stu "
                   + "FROM School S JOIN County C ON S.county_id=C.id "
                   + "WHERE C.name IN(" + inClause + ") "
                   + "GROUP BY C.name;";
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            for (int i = 0; i < counties.length; i++) ps.setString(i+1, counties[i]);
            ResultSet rs = ps.executeQuery();
            Map<String,Double> map = new LinkedHashMap<>();
            while (rs.next()) map.put(rs.getString(1), rs.getDouble(2));
            return map;
        }
    }

    // Задача 2
    public static Map<String, Double> avgExpenditure() throws Exception {
        String[] counties = {"Fresno","Contra Costa","El Dorado","Glenn"};
        String inClause = String.join(",", java.util.Collections.nCopies(counties.length, "?"));
        String sql = "SELECT C.name, AVG(M.expenditure) "
                   + "FROM School S JOIN County C ON S.county_id=C.id "
                   + "JOIN Metrics M ON S.id=M.school_id "
                   + "WHERE C.name IN(" + inClause + ") AND M.expenditure>10 "
                   + "GROUP BY C.name;";
        try (var c = DbUtil.getConnection();
             var ps = c.prepareStatement(sql)) {
            for (int i = 0; i < counties.length; i++) ps.setString(i+1, counties[i]);
            var rs = ps.executeQuery();
            Map<String,Double> map = new LinkedHashMap<>();
            while (rs.next()) map.put(rs.getString(1), rs.getDouble(2));
            return map;
        }
    }

    // Задача 3
    public static Map<String, String> topMath() throws Exception {
        int[][] ranges = {{5000,7500},{10000,11000}};
        Map<String,String> result = new LinkedHashMap<>();
        try (var c = DbUtil.getConnection()) {
            String sql = """
              SELECT S.name, M.math
              FROM School S JOIN Metrics M ON S.id=M.school_id
              WHERE S.students BETWEEN ? AND ?
              ORDER BY M.math DESC LIMIT 1;
            """;
            for (int[] rg : ranges) {
                try (var ps = c.prepareStatement(sql)) {
                    ps.setInt(1, rg[0]);
                    ps.setInt(2, rg[1]);
                    var rs = ps.executeQuery();
                    if (rs.next()) {
                        result.put(rg[0]+"–"+rg[1],
                                   rs.getString(1)+" (math="+rs.getDouble(2)+")");
                    } else {
                        result.put(rg[0]+"–"+rg[1], "Нет данных");
                    }
                }
            }
        }
        return result;
    }
}
