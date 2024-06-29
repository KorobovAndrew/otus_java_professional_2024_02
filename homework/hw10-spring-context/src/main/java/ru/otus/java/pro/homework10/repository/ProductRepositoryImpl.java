package ru.otus.java.pro.homework10.repository;

import org.springframework.stereotype.Repository;
import ru.otus.java.pro.homework10.entity.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private final List<Product> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();
    }

    @PostConstruct
    public void postConstructInit(){
        products.add(new Product(1L, "яблоко", 11));
        products.add(new Product(2L, "груша", 12));
        products.add(new Product(3L, "виноград", 13));
        products.add(new Product(4L, "киви", 14));
        products.add(new Product(5L, "банан", 15));
        products.add(new Product(6L, "апельсин", 16));
        products.add(new Product(7L, "персик", 17));
        products.add(new Product(8L, "арбуз", 18));
        products.add(new Product(9L, "лимон ", 19));
        products.add(new Product(10L, "ананас", 20));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("По указанному id отсутствуют объекты в репозитории"));
    }
}
