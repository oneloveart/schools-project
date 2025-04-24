# schools-project (Java + SQLite + JFreeChart)
Проект демонстрирует полный цикл работы с данными из CSV:
1. **Парсинг CSV** в объекты Java  
2. **Сохранение** данных в **SQLite** (3-я нормальная форма) через JDBC  
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

![console-tasks](https://github.com/user-attachments/assets/c17b96a9-c84a-4d39-8511-ede0a92f18b5)

Генерируются диаграммы avg_students.png и avg_expenditure.png

![avg_students](https://github.com/user-attachments/assets/2e363876-ea67-42c8-a1c3-3aea664fc1e5)
![avg_expenditure](https://github.com/user-attachments/assets/bf3d0d77-152b-4a23-b755-c6f1020b7e23)


