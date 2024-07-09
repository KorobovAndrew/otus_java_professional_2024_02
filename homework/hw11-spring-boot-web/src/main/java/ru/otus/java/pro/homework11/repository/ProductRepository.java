package ru.otus.java.pro.homework11.repository;

import ru.otus.java.pro.homework11.dto.CreateProductDto;
import ru.otus.java.pro.homework11.entity.Product;

import java.util.List;
import java.util.NoSuchElementException;

public interface ProductRepository {
    Product create(CreateProductDto createProductDto);
    List<Product> getAll();
    Product getById(Long id) throws NoSuchElementException;
}
