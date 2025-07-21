package com.ryansstore.store;

public interface PojoUserRepository {
    public void saveUser(PojoUser user);
    public boolean userExists(PojoUser user);
}
