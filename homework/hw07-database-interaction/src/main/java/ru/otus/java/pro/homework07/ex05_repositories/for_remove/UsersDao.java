package ru.otus.java.pro.homework07.ex05_repositories.for_remove;

import ru.otus.java.pro.homework07.ex05_repositories.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
    List<User> findAll() throws SQLException;
}
