package com.ryansstore.store.repositories;

import com.ryansstore.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
