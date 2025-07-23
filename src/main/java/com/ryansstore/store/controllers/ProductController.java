package com.ryansstore.store.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import com.ryansstore.store.entities.Product;
import com.ryansstore.store.dtos.ProductDto;
import com.ryansstore.store.mappers.ProductMapper;
import com.ryansstore.store.repositories.ProductRepository;
import com.ryansstore.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    public final ProductRepository productRepository;
    public final CategoryRepository categoryRepository;
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

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, UriComponentsBuilder uriBuilder) {
        System.out.println(productDto);
        if(productDto == null)
            return ResponseEntity.badRequest().build();

        Product product = productMapper.toEntity(productDto);
        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Invalid category ID")));

        productRepository.save(product);
        productDto.setId(product.getId());

        URI uri =  uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }
}
