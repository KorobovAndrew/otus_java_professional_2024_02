package ru.otus.java.pro.homework10.repository;

import ru.otus.java.pro.homework10.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
    Product getProductById(Long id);
}
