package ru.otus.java.pro.homework03.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    OPEN("Открыта"),
    CLOSED("Закрыта"),
    IN_PROGRESS("В работе"),
    TODO("В планах");

    private String value;
}
