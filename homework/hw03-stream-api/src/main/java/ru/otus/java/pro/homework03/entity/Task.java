package ru.otus.java.pro.homework03.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private Status status;
}
