package ru.otus.java.pro.homework11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private Double price;
}
