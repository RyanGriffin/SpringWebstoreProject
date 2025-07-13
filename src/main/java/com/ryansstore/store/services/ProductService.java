package com.ryansstore.store.services;

import com.ryansstore.store.entities.Category;
import com.ryansstore.store.entities.Product;
import com.ryansstore.store.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ryansstore.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void deleteProductById(Long id) { productRepository.deleteById(id); }

    @Transactional
    public void addProductWithCategory(String name, BigDecimal price, String description, Byte categoryId) {
        var product = Product.builder()
                .name(name)
                .price(price)
                .description(description)
                .build();

        var category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        productRepository.save(product);
    }

    // demonstration of custom queries with @Query annotation
    @Transactional
    public void increaseProductPrices(BigDecimal priceIncrease, Byte categoryID) {
        productRepository.updatePriceByCategory(priceIncrease, categoryID);
    }

    public void fetchProducts(byte categoryID) {
        var products = productRepository.findByCategory(new Category(categoryID));
        products.forEach(System.out::println);
    }
}
