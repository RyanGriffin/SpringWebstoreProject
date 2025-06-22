package com.ryansstore.store;

public interface UserRepository {
    public void saveUser(User user);
    public boolean userExists(User user);
}
