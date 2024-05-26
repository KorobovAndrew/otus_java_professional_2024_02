package ru.otus.java.pro.homework06.dao;

import lombok.SneakyThrows;
import ru.otus.java.pro.homework06.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDao implements Dao<Student> {
    private Connection connection;

    private StudentDao() {
    }

    public static ProxyDao create() {
        return new ProxyDao();
    }

    @Override
    public void save(Student student) {
        String studentSaveQuery = "INSERT INTO students VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(studentSaveQuery);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static class ProxyDao implements Dao<Student> {
        private StudentDao studentDao = new StudentDao();

        @SneakyThrows
        @Override
        public void save(Student student) {
            try {
                studentDao.connection = getNewConnection();
                studentDao.connection.setAutoCommit(false);
                studentDao.save(student);
                studentDao.connection.commit();
            } catch (SQLException e) {
                studentDao.connection.rollback();
                throw new RuntimeException(e);
            } finally {
                studentDao.connection.close();
            }
        }
    }

    private static Connection getNewConnection() throws SQLException {
        String url = "jdbc:h2:mem:mydb";
        String user = "sa";
        String passwd = "pass";
        return DriverManager.getConnection(url, user, passwd);
    }
}
