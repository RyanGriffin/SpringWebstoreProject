package com.ryansstore.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.query.Param;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.dtos.UserDto;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithAddresses();

    @Query("SELECT u.id AS id, u.email AS email FROM User u JOIN Profile p ON u.id = p.id WHERE p.loyaltyPoints > :points ORDER BY u.email")
    List<UserDto> findQualifiedUsers(@Param("points") int points);
}
