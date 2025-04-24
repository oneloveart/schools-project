# schools-project (Java + SQLite + JFreeChart)
Таблица №2 "Школы"
Проект демонстрирует полный цикл работы с данными из CSV:
1. **Парсинг CSV** в объекты Java  
2. **Сохранение** данных в **SQLite** через JDBC  
3. **SQL-запросы** к базе (3 задачи) и вывод результатов в консоль  
4. **Визуализация** числовых результатов через **JFreeChart** (диаграммы)  
5. **Сборка и запуск** через **Maven**  

## Структура проекта
schools-project/

├── pom.xml

├── Школы.csv

├── src/

│   └── main/

│       └── java/

│           └── com/

│               └── example/

│                   └── schools/

│                       ├── Main.java

│                       ├── csv/

│                       │   └── CsvLoader.java

│                       ├── db/

│                       │   ├── DbUtil.java

│                       │   ├── DbPopulator.java

│                       │   └── Queries.java

│                       └── chart/

│                           └── ChartUtils.java


## Последовательность работы

1. **Клонирование и подготовка**  
   ```bash
   git clone https://github.com/oneloveart/schools-project.git
   cd schools-project

2. **Сборка проекта**
   mvn clean compile
3. **Запуск приложения**
mvn exec:java
4. **Создаётся/инициализируется schools.db,
CSV загружается в таблицы,
В консоль выводятся результаты трёх задач**

![console-tasks](https://github.com/user-attachments/assets/f35e2fa1-0eb2-4289-bb1f-8e64d5820647)


Генерируются диаграммы avg_students.png и avg_expenditure.png


![avg_expenditure](https://github.com/user-attachments/assets/9edd0af0-e3f1-4c60-88d3-8e1d950940df)
![avg_students](https://github.com/user-attachments/assets/46d4a792-3a80-4ba0-a725-8beab9b2fc34)


