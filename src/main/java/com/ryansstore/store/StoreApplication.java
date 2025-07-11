package com.ryansstore.store;

import com.ryansstore.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.ryansstore.store.repositories.*;
import com.ryansstore.store.services.*;
import com.ryansstore.store.entities.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

// @EnableScheduling
@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        // ----- EXERCISE: Managing Products and Wishlists
        // /*
        var categoryRepository = context.getBean(CategoryRepository.class);
        var productRepository = context.getBean(ProductRepository.class);
        var userRepository = context.getBean(UserRepository.class);

        // Step 1: create the 'Food' category
//        var category = Category.builder()
//                .name("Food")
//                .build();
//
//        categoryRepository.save(category);


        // Step 2: add product to existing category
//        // retrieve existing category
//        var category = categoryRepository.findById((byte)1).orElseThrow(() -> new RuntimeException("Category not found"));
//
//        // create new product and assign it the fetched category
//        var product = Product.builder()
//                .name("beans")
//                .price(new BigDecimal(10))
//                .description("just some beans")
//                .build();
//        category.addProduct(product);
//        productRepository.save(product);

        // Step 3: add product to user's wishlist
        // fetch a user and add all products to wishlist
//        var user = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));
//        Set<Product> userWishlist = new HashSet<>();
//        productRepository.findAll().forEach(userWishlist::add);
//        user.setWishList(userWishlist);
//        userRepository.save(user);

        // Step 4: Delete an existing product
        var productService = context.getBean(ProductService.class);
        productService.deleteProductById(5L);
        // */

        // ----- EXERCISE: persisting related entities
        /*
        var service = context.getBean(UserService.class);
        service.deleteRelated(2L);
        service.deleteAddressOfUser(10L);
        */

        // ----- EXERCISE: utilizing fetching strategies
        /*
        var service = context.getBean(UserService.class);
        var address = service.fetchAddress(1L);
        System.out.println(address.getFullAddress());

        // WRONG APPROACH: should fetch address by user -> create fetchAddress() in UserService
        // var addressRepo = context.getBean(AddressRepository.class);
        // var address = addressRepo.findById(1L).orElseThrow();
        */

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
