package com.ryansstore.store.controllers;

import com.ryansstore.store.entities.Product;
import org.springframework.web.bind.annotation.*;
import com.ryansstore.store.repositories.ProductRepository;
import com.ryansstore.store.mappers.ProductMapper;
import com.ryansstore.store.dtos.ProductDto;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    public final ProductRepository productRepository;
    public final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAllProducts(@RequestParam(name = "categoryId", required = false) Byte id) {
        if(id != null) {
            return productRepository.findByCategoryId(id)
                    .stream()
                    .map(productMapper::toDto)
                    .toList();
        }

        return productRepository.findAllWithCategory()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductsById(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
