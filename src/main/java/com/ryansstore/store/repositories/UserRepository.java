package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.User;
import com.ryansstore.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = "tags")
    Optional<User> findByEmail(String email);
}
