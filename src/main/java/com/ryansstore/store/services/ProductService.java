package com.ryansstore.store.services;

import com.ryansstore.store.entities.Product;
import com.ryansstore.store.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("bean");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchProducts(BigDecimal minPrice, BigDecimal maxPrice) {
        var products = productRepository.findProducts(minPrice, maxPrice);
        products.forEach(System.out::println);
    }
}
