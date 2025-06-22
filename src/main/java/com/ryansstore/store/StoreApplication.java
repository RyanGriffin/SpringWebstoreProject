package com.ryansstore.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var OrderService = context.getBean(OrderService.class);
        var Notif = context.getBean(NotificationManager.class);
        var UserServ = context.getBean(UserService.class);


        User testUser = new User(69, "user1@ryansStore.com", "badpass", "user1");
        UserServ.registerUser(testUser);
        // UserServ.registerUser(testUser);
        OrderService.placeOrder();
        Notif.sendNotification("Congratulations! Your order has been placed!", "ryan@ryan.com");
    }

}
