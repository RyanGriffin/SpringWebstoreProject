package com.ryansstore.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ryansstore.store.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}