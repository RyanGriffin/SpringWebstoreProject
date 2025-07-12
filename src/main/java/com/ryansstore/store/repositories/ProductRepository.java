package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // Strings!
    List<Product> findByName(String name); // SELECT * FROM products WHERE name = ?
    List<Product> findByNameLike(String name); // SELECT * FROM products WHERE name LIKE ?
    List<Product> findByNameNotLike(String name); // SELECT * FROM products WHERE name NOT LIKE ?
    List<Product> findByNameContaining(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameEndingWithIgnoreCase(String name);

    // Numbers
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal price);

    // Null
    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();

    // Multiple Conditions
    List<Product> findByDescriptionNullAndNameNull();

    // Sort (ORDER BY)
    List<Product> findByNameOrderByPrice(String name);
    List<Product> findByNameOrderByPriceDesc(String name);

    // Limit
    List<Product> findTop5ByName(String name);
    List<Product> findFirst5ByNameLikeOrderByPrice(String name);
}