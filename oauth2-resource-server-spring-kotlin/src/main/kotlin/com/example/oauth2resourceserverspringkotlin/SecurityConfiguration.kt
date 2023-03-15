package com.example.oauth2resourceserverspringkotlin


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain

const val PERMISSION_JWT_KEY = "permissions"

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity):SecurityFilterChain {
        http.csrf().disable()
            .httpBasic().disable()
            .sessionManagement().disable()
            .logout().disable()
            .requestCache().disable()
            .securityContext().disable()
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter())
        return http.build()
    }

    private fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val converter = JwtAuthenticationConverter()
        converter.setJwtGrantedAuthoritiesConverter { jwt ->
            jwt.getClaimAsStringList(PERMISSION_JWT_KEY)
                .map { permission ->
                    SimpleGrantedAuthority(permission)
                }
        }
        return converter
    }
}

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration() {
    override fun createExpressionHandler(): MethodSecurityExpressionHandler {
        val expressionHandler = DefaultMethodSecurityExpressionHandler()
        expressionHandler.setPermissionEvaluator(CustomPermissionEvaluator())
        return expressionHandler
    }
}
