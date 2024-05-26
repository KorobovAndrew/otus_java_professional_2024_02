package ru.otus.java.pro.homework06.dao;

import java.util.List;

public interface Dao<T> {
    void save(T t);
}
