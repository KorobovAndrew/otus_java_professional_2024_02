package ru.otus.java.pro.homework11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.java.pro.homework11.dto.CreateProductDto;
import ru.otus.java.pro.homework11.entity.Product;
import ru.otus.java.pro.homework11.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public Product create(@RequestBody CreateProductDto createProductDto){
        return productService.create(createProductDto);
    }

    @GetMapping("/get/{id}")
    public Product get(@PathVariable Long id){
        return productService.getById(id);
    }

    @GetMapping("/get-all")
    public List<Product> getAll(){
        return productService.getAll();
    }
}
