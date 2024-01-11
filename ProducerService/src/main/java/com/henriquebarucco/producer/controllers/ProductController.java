package com.henriquebarucco.producer.controllers;

import com.henriquebarucco.producer.dtos.ProductDTO;
import com.henriquebarucco.producer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> produces(@RequestBody ProductDTO dto) {
        productService.createProduct(dto);
        return ResponseEntity.status(CREATED).body(dto);
    }
}
