package com.example.schools.csv;

import com.example.schools.model.School;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    public static List<School> load(String path) throws Exception {
        List<School> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] row;
            // Пропустить заголовок
            reader.readNext();
            while ((row = reader.readNext()) != null) {
                School s = new School();
                s.setDistrict(Integer.parseInt(row[1]));
                s.setName(row[2]);
                s.setCounty(row[3]);
                s.setGrades(row[4]);
                s.setStudents(Integer.parseInt(row[5]));
                s.setTeachers(Double.parseDouble(row[6]));
                s.setCalworks(Double.parseDouble(row[7]));
                s.setLunch(Double.parseDouble(row[8]));
                s.setComputer(Integer.parseInt(row[9]));
                s.setExpenditure(Double.parseDouble(row[10]));
                s.setIncome(Double.parseDouble(row[11]));
                s.setEnglish(Double.parseDouble(row[12]));
                s.setRead(Double.parseDouble(row[13]));
                s.setMath(Double.parseDouble(row[14]));
                list.add(s);
            }
        }
        return list;
    }
}
