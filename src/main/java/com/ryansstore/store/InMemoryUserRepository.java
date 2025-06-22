package com.ryansstore.store;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.util.HashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private HashMap<String, User> users;

    public InMemoryUserRepository() {
        users = new HashMap<>();
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getEmail(), user);
        System.out.println("User " + user.getName() + " with email " + user.getEmail() + " has been saved!");
    }

    @Override
    public boolean userExists(User user) {
        if(users.containsKey(user.getEmail())) {
            System.out.println("ERROR: User " + user.getName() + " with email " + user.getEmail() + " already exists!");
            return true;
        }
        return false;
    }
}
