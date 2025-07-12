package com.ryansstore.store.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ryansstore.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void deleteProductById(Long id) { productRepository.deleteById(id); }

    // demonstration of custom queries with @Query annotation
    @Transactional
    public void increaseProductPrices(BigDecimal priceIncrease, Byte categoryID) {
        productRepository.updatePriceByCategory(priceIncrease, categoryID);
    }
}
