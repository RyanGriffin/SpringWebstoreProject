package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // saving for later reference:
    /*
    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithAddresses();

    @Query("SELECT u.id AS id, u.email AS email FROM User u JOIN Profile p ON u.id = p.id WHERE p.loyaltyPoints > :points ORDER BY u.email")
    List<UserDto> findQualifiedUsers(@Param("points") int points);
    */
}
