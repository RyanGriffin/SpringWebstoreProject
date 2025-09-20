package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.CartItem;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface CartItemRepository extends CrudRepository<CartItem, UUID> {
}
