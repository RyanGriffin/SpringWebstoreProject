package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = "category")
    List<Product> findByCategoryId(Byte id);

    @EntityGraph(attributePaths = "category")
    @Query("SELECT p FROM Product p")
    List<Product> findAllWithCategory();

    // Examples of Derived Query methods:
    /*
    // Strings!
    List<Product> findByName(String name); // SELECT * FROM products WHERE name = ?
    List<Product> findByNameLike(String name); // SELECT * FROM products WHERE name LIKE ?
    List<Product> findByNameNotLike(String name); // SELECT * FROM products WHERE name NOT LIKE ?
    List<Product> findByNameContaining(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameEndingWithIgnoreCase(String name);

    // Numbers
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price);
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

    // Find first 5 products whose prices are in a given range and sort by name
    List<Product> findFirst5ByPriceBetweenOrderByNameDesc(BigDecimal minPrice, BigDecimal maxPrice);

    // above method name is a lil long, so we can write our own with the @Query
    // Custom queries can be written in JPQL (like this one) or SQL
    @Query("select p.id, p.name from Product p where p.category = :category")
    List<Product> findByCategory(@Param("category") Category category);

    // can also use aggregate function (also an example of SQL)
    @Query(value = "SELECT COUNT(*) FROM products p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.name", nativeQuery = true)
    List<Product> countProducts(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    // and use @Modifying to update!
    @Modifying
    @Query(value = "UPDATE Product p SET p.price = p.price + :newPrice WHERE p.category.id = :categoryID")
    void updatePriceByCategory(@Param("newPrice") BigDecimal newPrice, @Param("categoryID") Byte categoryID);

    // Also can be stored procedure, like below
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal minPrice, BigDecimal maxPrice);
     */
}