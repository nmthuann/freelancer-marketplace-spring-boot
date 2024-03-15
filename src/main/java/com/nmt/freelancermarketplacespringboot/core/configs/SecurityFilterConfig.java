package com.nmt.freelancermarketplacespringboot.core.configs;


import com.nmt.freelancermarketplacespringboot.common.filters.AuthMiddlewareFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.Customizer;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfig {

//    @Autowired
//    AuthMiddlewareFilter authMiddlewareFilter;

    private final AuthMiddlewareFilter authMiddlewareFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**", "/public/**", "/user/**").permitAll() // public
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN") // admin
                        // .requestMatchers("/user/**").hasAnyAuthority("USER") // user
                        .anyRequest().authenticated()) // private
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(
                        authMiddlewareFilter, UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }




}
