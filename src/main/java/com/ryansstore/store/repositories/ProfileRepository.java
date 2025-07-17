package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    // Below method name is long and unreadable, has been simplified with @Query (now found in UserRepository)
    @EntityGraph(attributePaths = "user")
    List<Profile> findByLoyaltyPointsGreaterThanOrderByUserEmail(int points);
}