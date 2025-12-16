package com.ryansstore.store;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

    @Bean
    public ApplicationRunner filterDebug(org.springframework.context.ApplicationContext ctx) {
        return args -> {
            System.out.println("=== Filters ===");
            ctx.getBeansOfType(jakarta.servlet.Filter.class)
                    .forEach((name, f) -> System.out.println(name + " -> " + f.getClass().getName()));
        };
    }
}
