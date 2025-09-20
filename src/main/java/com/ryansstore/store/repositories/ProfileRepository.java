package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    // Below method name is long/unreadable, can be simplified with @Query below
    @EntityGraph(attributePaths = "user")
    List<Profile> findByLoyaltyPointsGreaterThanOrderByUserEmail(int points);

    // This fetches users, so should be in UserRepository.java, but leaving it here for later reference
    @Query("SELECT u.id AS id, u.email AS email FROM User u JOIN Profile p ON u.id = p.id WHERE p.loyaltyPoints > :points ORDER BY u.email")
    List<Profile> findQualifiedUsers(@Param("points") int points);
}