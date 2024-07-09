package ru.otus.java.pro.homework11.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.homework11.dto.CreateProductDto;
import ru.otus.java.pro.homework11.entity.Product;
import ru.otus.java.pro.homework11.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Override
    public Product create(CreateProductDto createProductDto) {
        return productRepository.create(createProductDto);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(Long id) {
        Product product = null;
        try {
            product = productRepository.getById(id);
        } catch (NoSuchElementException ex){
            LOG.error(ex.getMessage());
        }
        return product;
    }
}
