package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.Category;
import com.ryansstore.store.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        // SQL: FROM products
        Root<Product> root = cq.from(Product.class);

        // Conditions for SQL query below:
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            // SQL: name like %name%
            predicates.add(cb.like(root.get("name"), "%"+name+"%"));
        }
        if (minPrice != null) {
            // SQL: price >= minPrice
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            // SQL: price <= maxPrice
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        // SQL: SELECT ... WHERE
        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Product> findProductsByCriteria(String productName, String categoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        // SQL: FROM products p
        Root<Product> root = cq.from(Product.class);
        // SQL: JOIN categories c ON c.id = p.category_id
        Join<Product, Category> join = root.join("category");

        // Conditions for SQL query below:
        List<Predicate> predicates = new ArrayList<>();
        if(productName != null) {
            // SQL: p.name like %productName%
            predicates.add(cb.like(root.get("name"), "%"+productName+"%"));
        }
        if(categoryName != null) {
            // SQL: c.name like %categoryName%
            predicates.add(cb.like(join.get("name"), "%"+categoryName+"%"));
        }

        // SQL: SELECT ... WHERE
        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
