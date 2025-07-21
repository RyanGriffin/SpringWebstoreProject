package com.ryansstore.store;

import java.util.HashMap;

// No longer needed with AppConfig class approach
/*import org.springframework.stereotype.Repository;

@Repository*/
public class InMemoryUserRepository implements PojoUserRepository {
    private HashMap<String, PojoUser> users;

    public InMemoryUserRepository() { users = new HashMap<>(); }

    @Override
    public void saveUser(PojoUser user) {
        users.put(user.getEmail(), user);
        System.out.println("User " + user.getName() + " with email " + user.getEmail() + " has been saved!");
    }

    @Override
    public boolean userExists(PojoUser user) {
        if(users.containsKey(user.getEmail())) {
            System.err.println("ERROR: PojoUser " + user.getName() + " with email " + user.getEmail() + " already exists!");
            return true;
        }
        return false;
    }
}
