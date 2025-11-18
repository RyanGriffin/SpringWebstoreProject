package com.ryansstore.store.admin;

import org.springframework.stereotype.Component;
import com.ryansstore.store.common.SecurityRules;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.ryansstore.store.users.Role;

@Component
public class AdminSecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers("/admin/**").hasRole(Role.ADMIN.name());
    }
}

