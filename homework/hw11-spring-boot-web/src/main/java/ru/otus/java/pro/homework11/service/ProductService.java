package ru.otus.java.pro.homework11.service;

import ru.otus.java.pro.homework11.dto.CreateProductDto;
import ru.otus.java.pro.homework11.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(CreateProductDto createProductDto);
    List<Product> getAll();
    Product getById(Long id);
}
