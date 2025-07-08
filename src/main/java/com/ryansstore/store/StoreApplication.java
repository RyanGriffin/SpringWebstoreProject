package com.ryansstore.store;

import com.ryansstore.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.ryansstore.store.entities.*;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.repositories.UserRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.math.BigDecimal;

// @EnableScheduling
@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        // ----- EXERCISE: understanding fetching strategies
        // /*
        var service = context.getBean(UserService.class);
        var address = service.fetchAddress(1L);
        System.out.println(address.getFullAddress());

        // WRONG APPROACH: should fetch address by user -> create fetchAddress() in UserService
        // var addressRepo = context.getBean(AddressRepository.class);
        // var address = addressRepo.findById(1L).orElseThrow();
        // */

        // ----- EXERCISE: demonstration of entity states
        /*
        var service = context.getBean(UserService.class);
        service.showEntityStates();
        service.showRelatedEntities();
        */

        // ----- EXERCISE: display registered user's emails using repository
        /*
        var repo = context.getBean(UserRepository.class);
        repo.findAll().forEach(u -> System.out.println(u.getEmail()));
        // repo.deleteById(1L);

        // var user = repo.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        // System.out.println(user.getEmail());
        */

        // ----- EXERCISE: insert a user into db using repositories
        /*
        var repo = context.getBean(UserRepository.class);

        var user = User.builder()
                .name("John")
                .email("john@ryan.com")
                .password("weakpassword")
                .build();

        repo.save(user);
        */

        // ----- EXERCISE: create and set user and profile entities
        /*
        var user = User.builder()
                .name("John")
                .password("pass")
                .email("john@ryansStore.com")
                .phoneNumber("(555)555-5555")
                .build();

        var prof = Profile.builder()
                .bio("whattup")
                .build();

        user.setProfile(prof);
        System.out.println(user);
         */

        // ----- EXERCISE: create and set Product and Category entities
        /*
        var prod = Product.builder()
                .name("Beans")
                .price(new BigDecimal(420.69))
                .build();

        var cat = Category.builder().name("Food").build();

        cat.addProduct(prod);
        System.out.println(prod);
        System.out.println(cat);
         */

        // ----- INITIAL SET UP: written before switch to Spring Data JPA
        /*
        var OrderService = context.getBean(OrderService.class);
        var Notif = context.getBean(NotificationManager.class);
        var UserServ = context.getBean(UserService.class);

        com.ryansstore.store.User testUser = new com.ryansstore.store.User(69, "user1@ryan.com", "badpass", "user1", "(555)555-5555");
        UserServ.registerUser(testUser);
        // UserServ.registerUser(testUser); // used to test duplicate user handling
        OrderService.placeOrder("stripe", 10.00);
        OrderService.placeOrder("paypal", 10.00);
        Notif.sendNotification("email","Congratulations! Your order has been placed!", testUser.getEmail());
        */
    }
}
