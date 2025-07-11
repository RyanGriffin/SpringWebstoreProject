package com.ryansstore.store.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ryansstore.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id); }
}
