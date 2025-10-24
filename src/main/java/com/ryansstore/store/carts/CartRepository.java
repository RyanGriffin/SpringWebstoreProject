package com.ryansstore.store.carts;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends CrudRepository<Cart, UUID> {
    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT c FROM Cart c WHERE c.id = :id")
    Optional<Cart> getCartWithItems(@Param("id") UUID cartId);
}
