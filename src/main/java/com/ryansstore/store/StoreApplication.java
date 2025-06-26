package com.ryansstore.store;


import com.ryansstore.store.entities.*;
import com.ryansstore.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;

@SpringBootApplication
// @EnableScheduling
public class StoreApplication {

    public static void main(String[] args) {
//        var user = User.builder()
//                .name("John")
//                .password("pass")
//                .email("john@ryansStore.com")
//                .build();
//
//        var prof = Profile.builder()
//                .bio("whattup")
//                .build();
//
//        user.setProfile(prof);
//        System.out.println(user);
        var prod = Product.builder()
                .name("Beans")
                .price(new BigDecimal(420.69))
                .build();

        var cat = Category.builder().name("Food").build();

        cat.addProduct(prod);
        System.out.println(prod);
        System.out.println(cat);

        /* ----- INITIAL SET UP, WRITTEN BEFORE DIVING INTO SPRING DATA JPA
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var OrderService = context.getBean(OrderService.class);
        var Notif = context.getBean(NotificationManager.class);
        var UserServ = context.getBean(UserService.class);


        com.ryansstore.store.User testUser = new com.ryansstore.store.User(69, "user1@ryansStore.com", "badpass", "user1");
        UserServ.registerUser(testUser);
        // UserServ.registerUser(testUser); // used to test duplicate user handling
        OrderService.placeOrder();
        Notif.sendNotification("Congratulations! Your order has been placed!", "ryan@ryan.com");
        */
    }
}
