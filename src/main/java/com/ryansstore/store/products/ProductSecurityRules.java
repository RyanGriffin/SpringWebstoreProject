package com.ryansstore.store.products;

import org.springframework.stereotype.Component;
import com.ryansstore.store.common.SecurityRules;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;
import com.ryansstore.store.users.Role;

@Component
public class ProductSecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/products/**").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/products/**").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole(Role.ADMIN.name());
    }
}
