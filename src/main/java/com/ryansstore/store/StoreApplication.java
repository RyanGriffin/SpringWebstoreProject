package com.ryansstore.store;

import com.ryansstore.store.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.math.BigDecimal;

@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        // ----- EXERCISE: Writing Dynamic Queries
        var service = context.getBean(ProductService.class);
        System.out.println("\nFetching products using Criteria:");
        service.fetchProductsByCriteria("bean", "food");
        service.fetchProductsByCriteria("bean", BigDecimal.valueOf(1),  BigDecimal.valueOf(15));

        System.out.println("\nFetching products using Specification:");
        service.fetchProductsBySpecification("bean", "food");
        service.fetchProductsBySpecification("bean", BigDecimal.valueOf(1),  BigDecimal.valueOf(15));

        // ----- INITIAL SET UP:
        // - Plain Old Java Object (POJO) approach, no entities
        // - written before switch to Spring Data JPA
        /*
        var OrderService = context.getBean(OrderService.class);
        var Notif = context.getBean(NotificationManager.class);
        var UserServ = context.getBean(UserService.class);

        com.ryansstore.store.User testUser = new com.ryansstore.store.User(69, "user1@ryan.com", "badpass", "user1", "(555)555-5555");
        UserServ.registerUser(testUser);
        // UserServ.registerUser(testUser); // used to test duplicate user handling
        OrderService.placeOrder("stripe", 10.00);
        // OrderService.placeOrder("paypal", 10.00);
        Notif.sendNotification("email","Congratulations! Your order has been placed!", testUser.getEmail());
        */
    }
}
