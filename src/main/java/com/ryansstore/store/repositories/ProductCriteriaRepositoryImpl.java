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

        Root<Product> root = cq.from(Product.class); // SQL: FROM products

        List<Predicate> predicates = new ArrayList<>(); // Conditions for SQL query
        if (name != null)
            predicates.add(cb.like(root.get("name"), "%"+name+"%")); // SQL: name like %name%
        if (minPrice != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));  // SQL: price >= minPrice
        if (maxPrice != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));  // SQL: price <= maxPrice

        cq.select(root).where(predicates.toArray(new Predicate[0])); // SQL: SELECT ... WHERE

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Product> findProductsByCriteria(String productName, String categoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class); // SQL: FROM products p
        Join<Product, Category> join = root.join("category"); // SQL: JOIN categories c ON c.id = p.category_id

        List<Predicate> predicates = new ArrayList<>(); // Conditions for SQL query
        if(productName != null)
            predicates.add(cb.like(root.get("name"), "%"+productName+"%")); // SQL: p.name like %productName%
        if(categoryName != null)
            predicates.add(cb.like(join.get("name"), "%"+categoryName+"%")); // SQL: c.name like %categoryName%

        cq.select(root).where(predicates.toArray(new Predicate[0])); // SQL: SELECT ... WHERE

        return entityManager.createQuery(cq).getResultList();
    }
}
