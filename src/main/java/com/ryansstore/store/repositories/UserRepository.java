package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.User;
import com.ryansstore.store.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    // Strings!
    List<User> findByName(String name); // SELECT * FROM users WHERE name = ?
    List<User> findByNameLike(String name); // SELECT * FROM users WHERE name LIKE ?
    List<User> findByNameNotLike(String name); // SELECT * FROM users WHERE name NOT LIKE ?
    List<User> findByNameContaining(String name);
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameEndingWithIgnoreCase(String name);

    // Multiple Conditions
    List<User> findByNameStartingWithAndEmailEndingWithIgnoreCaseOrderById(String name, String email);

    // Sort (ORDER BY)
    List<User> findByNameOrderById(String name);
    List<User> findByNameOrderByIdDesc(String name);

    // Limit
    List<User> findTop5ByName(String name);
    List<User> findFirst5ByNameLikeOrderByEmail(String name);
}
