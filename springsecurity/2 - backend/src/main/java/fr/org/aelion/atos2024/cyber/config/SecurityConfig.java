package fr.org.aelion.atos2024.cyber.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import fr.org.aelion.atos2024.cyber.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // TODO: enable and config CSRF
                .addFilterAfter((servletRequest, servletResponse, filterChain) -> {
                    // transform to http request object
                    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
                    // get header Authorization content
                    String authorization = httpRequest.getHeader("Authorization");
                    // if token ok in Authorization
                    // TODO: create token validation logic
                    if(authorization != null) {
                        String token = authorization.replace("Bearer ", "");
                        if(!token.isEmpty()){
                            DecodedJWT jwt = TokenUtils.decodeJWT(token);
                            // TODO: Handle token verification...
                            if(jwt != null && !jwt.getClaim("email").isNull()) {
                                // Generate Spring Security Auth
                                SecurityContextHolder.getContext().setAuthentication(
                                        UsernamePasswordAuthenticationToken.authenticated(jwt.getClaim("email").asString(), token, null) // authorities pour les rôles
                                );
                            }
                        }
                    }
                    // do next filter
                    filterChain.doFilter(servletRequest, servletResponse);
                }, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(filter -> {
                    // /auth/signin /auth/signup, => authorize requests from /auth/*
                    filter.requestMatchers("auth/*").permitAll()
                            .requestMatchers("admin/*").hasRole("ADMIN")
                            .anyRequest().authenticated();
                }).build();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        return null;
    }*/

}