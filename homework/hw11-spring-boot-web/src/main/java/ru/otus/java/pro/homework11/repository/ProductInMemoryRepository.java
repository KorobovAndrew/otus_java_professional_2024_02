package ru.otus.java.pro.homework11.repository;

import org.springframework.stereotype.Component;
import ru.otus.java.pro.homework11.dto.CreateProductDto;
import ru.otus.java.pro.homework11.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ProductInMemoryRepository implements ProductRepository {
    private List<Product> products;
    private static Long nextId;

    public ProductInMemoryRepository() {
        products = new ArrayList<>();
        nextId = 1L;
    }

    @Override
    public Product create(CreateProductDto createProductDto) {
        Product product = new Product(nextId, createProductDto.getTitle(), createProductDto.getPrice());
        products.add(product);
        nextId++;
        return product;
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(Long id) throws NoSuchElementException{
        return products.stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("object is missing"));
    }
}
