package com.ryansstore.store.repositories;

import com.ryansstore.store.dtos.ProductSummary;
import com.ryansstore.store.entities.Category;
import com.ryansstore.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository {
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
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

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

    // Find products whose prices are in a given range and sort by name
    List<Product> findByPriceBetweenOrderByName(BigDecimal minPrice, BigDecimal maxPrice);

    // above query is a little long, so we can do this with the @Query annotation...
    // can be SQL or JPQL
    // Also can be stored procedure, like below
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal minPrice, BigDecimal maxPrice);

    // can also use aggregate function...
    @Query(value = "SELECT COUNT(*) FROM products p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.name", nativeQuery = true)
    List<Product> countProducts(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    // and use this to update! (Using JPQL for this)
    @Modifying
    @Query(value = "UPDATE Product p SET p.price = p.price + :newPrice WHERE p.category.id = :categoryID")
    void updatePriceByCategory(@Param("newPrice") BigDecimal newPrice, @Param("categoryID") Byte categoryID);

    @Query("select p.id, p.name from Product p where p.category = :category")
    List<ProductSummary> findByCategory(@Param("category") Category category);
}