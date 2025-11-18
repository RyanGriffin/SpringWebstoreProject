package com.ryansstore.store.carts;

import org.springframework.stereotype.Component;
import com.ryansstore.store.common.SecurityRules;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Component
public class CartSecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers("/carts/**").permitAll();
    }
}

