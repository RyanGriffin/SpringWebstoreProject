package com.ryansstore.store.repositories.specifications;

import com.ryansstore.store.entities.Category;
import com.ryansstore.store.entities.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;

public class ProductSpec {
    // returns a specification for filtering products by name
    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%"+name+"%");
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasCategoryName(String name) {
        return (root, query, cb) -> {
            Join<Product, Category> join = root.join("category");
            return cb.like(join.get("name"), "%"+name+"%");
        };
    }
}
