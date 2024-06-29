package ru.otus.java.pro.homework10.entity;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.java.pro.homework10.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Getter
@Scope("prototype")
public class Cart {
    private final ProductRepository productRepository;
    private final List<Product> products;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.products = new ArrayList<>();;
    }

    public Product addProductById(Long id) throws NoSuchElementException {
        var product = productRepository.getProductById(id);
        products.add(productRepository.getProductById(id));
        return product;
    }

    public Product deleteProductById(Long id) throws NoSuchElementException{
        var product = productRepository.getProductById(id);
        if(!products.contains(product)){
            throw new NoSuchElementException("Товар отсутствует в корзине");
        }
        products.remove(product);
        return product;
    }
}
