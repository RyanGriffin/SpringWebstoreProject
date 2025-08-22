package com.ryansstore.store.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import com.ryansstore.store.entities.Product;
import com.ryansstore.store.entities.Category;
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
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null)
            return ResponseEntity.badRequest().build();

        Product product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());

        URI uri =  uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null)
            return ResponseEntity.badRequest().build();

        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        productMapper.updateEntity(productDto, product);
        productDto.setId(product.getId());
        product.setCategory(category);
        productRepository.save(product);

        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }
}
