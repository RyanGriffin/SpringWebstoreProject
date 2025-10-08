package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    // saving for later reference: (@EntityGraph and custom queries w/ @Query)
    /*@EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithAddresses();

    @Query("SELECT u.id AS id, u.email AS email FROM User u JOIN Profile p ON u.id = p.id WHERE p.loyaltyPoints > :points ORDER BY u.email")
    List<UserDto> findQualifiedUsers(@Param("points") int points);
    */
}
