package com.ryansstore.store;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final NotificationService notifService;

    public UserService(UserRepository userRepo, NotificationService notifService) {
        this.userRepo = userRepo;
        this.notifService = notifService;
    }

    public void registerUser(User user) {
        if(!userRepo.userExists(user)) {
            // Step 1: save user
            userRepo.saveUser(user);

            // Step 2: send confirmation email
            notifService.sendNotification("user " + user.getName() + " has been successfully register!", user.getEmail());
        }
        else { // Optional: handle duplicate user:
            notifService.sendNotification("ERROR: user with email " + user.getEmail() + " already exists! Duplicate users are not supported.", user.getEmail());
            throw new IllegalArgumentException("a user with email \" + user.getEmail() + \" already exists! Duplicate users are not supported.");
        }

        /* WRONG APPROACH BELOW: NEED TO USE CONSTRUCTOR INJECTION!!!!
        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        userRepo.saveUser(user);

        NotificationService notifService = new NotificationService();
        notifService.sendNotification("user " + user.getName() + " has been successfully register!", user.getEmail());
        */
    }
}
