package com.example.schools.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbUtil {
    private static final String URL = "jdbc:sqlite:schools.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }

    public static void initDb() throws Exception {
        try (var conn = getConnection(); var stmt = conn.createStatement()) {
            // Таблица County
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS County (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  name TEXT UNIQUE NOT NULL
                );
            """);
            // Таблица School
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS School (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  district INTEGER NOT NULL,
                  name TEXT NOT NULL,
                  grades TEXT,
                  students INTEGER,
                  teachers REAL,
                  county_id INTEGER NOT NULL,
                  FOREIGN KEY(county_id) REFERENCES County(id)
                );
            """);
            // Таблица Metrics
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Metrics (
                  school_id INTEGER PRIMARY KEY,
                  calworks REAL, lunch REAL, computer INTEGER,
                  expenditure REAL, income REAL,
                  english REAL, read REAL, math REAL,
                  FOREIGN KEY(school_id) REFERENCES School(id)
                );
            """);
        }
    }
}
