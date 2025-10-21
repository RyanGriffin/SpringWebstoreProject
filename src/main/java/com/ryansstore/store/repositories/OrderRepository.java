package com.ryansstore.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ryansstore.store.entities.Order;
import com.ryansstore.store.entities.User;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(User customer);
}