package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.User;
import com.ryansstore.store.dtos.UserSummary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = "tags")
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithAddresses();

    @Query("SELECT u.id AS id, u.email AS email FROM User u JOIN Profile p ON u.id = p.id WHERE p.loyaltyPoints > :points ORDER BY u.email")
    List<UserSummary> findQualifiedUsers(@Param("points") int points);
}
