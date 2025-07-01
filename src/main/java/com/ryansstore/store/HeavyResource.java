package com.ryansstore.store;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// Used as an example for Lazy Initialization. Won't include in AppConfig.java
@Component
@Lazy
public class HeavyResource {
    public HeavyResource() {
        System.out.println("HeavyResource created");
    }
}
