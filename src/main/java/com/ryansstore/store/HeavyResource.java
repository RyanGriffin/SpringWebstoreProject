package com.ryansstore.store;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// Used as an example for Lazy Initialization
@Component
@Lazy
public class HeavyResource {
    public HeavyResource() {
        System.out.println("HeavyResource created");
    }
}
