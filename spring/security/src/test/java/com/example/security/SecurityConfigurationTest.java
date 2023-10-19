package com.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SecurityConfigurationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    ConfigurableListableBeanFactory configurableListableBeanFactory;

    @EnableWebSecurity(debug = true)
    @TestConfiguration
    public static class TestSecurityConfiguration {

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .httpBasic().disable()
                    .sessionManagement().disable()
                    .logout().disable()
                    .requestCache().disable()
                    .securityContext().disable();


            http.oauth2ResourceServer()
                    .jwt()
                    .jwtAuthenticationConverter(jwtAuthenticationConverter());
            return http.build();
        }

        private JwtAuthenticationConverter jwtAuthenticationConverter() {
            JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
            converter.setJwtGrantedAuthoritiesConverter(jwt -> {
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                List<String> permissionList = jwt.getClaimAsStringList("PERMISSION_JWT_KEY");
                for (String permission : permissionList) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }
                return Collections.unmodifiableCollection(authorities);
            });
            return converter;
        }
    }

    @Test
    public void check_security_filter_count() {

        displayBeanDefinitionNames();

        SecurityFilterChain filterChain = (SecurityFilterChain) context.getBean("filterChain");

        List<Filter> filters = filterChain.getFilters();

        System.out.println("filters: " + filters);

        assertEquals(6, filters.size());
    }

    private void displayBeanDefinitionNames() {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();

        System.out.println("Display bean definition names");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
}
