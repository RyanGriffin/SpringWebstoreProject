package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}