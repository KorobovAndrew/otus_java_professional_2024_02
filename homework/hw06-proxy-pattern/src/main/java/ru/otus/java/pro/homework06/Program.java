package ru.otus.java.pro.homework06;

import ru.otus.java.pro.homework06.dao.Dao;
import ru.otus.java.pro.homework06.dao.StudentDao;
import ru.otus.java.pro.homework06.entity.Student;

import java.sql.*;

public class Program {
    public static void main(String[] args) {
        try (Connection connection = getNewConnection()) {

            String customerTableQuery = "CREATE TABLE students " +
                    "(id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";

            Statement statement = connection.createStatement();
            statement.execute(customerTableQuery);

            Student student = new Student(1, "TestStudent", 99);

            Dao studentDao = StudentDao.create();

            studentDao.save(student);

            System.out.println();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getNewConnection() throws SQLException {
        String url = "jdbc:h2:mem:mydb";
        String user = "sa";
        String passwd = "pass";
        return DriverManager.getConnection(url, user, passwd);
    }
}
