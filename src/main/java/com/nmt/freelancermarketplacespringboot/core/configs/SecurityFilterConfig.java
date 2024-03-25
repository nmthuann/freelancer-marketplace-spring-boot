package com.nmt.freelancermarketplacespringboot.core.configs;


import com.nmt.freelancermarketplacespringboot.common.filters.AuthMiddlewareFilter;
import com.nmt.freelancermarketplacespringboot.components.security.DelegatedAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.Customizer;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfig {

    @Autowired
    // @Qualifier("delegatedAuthenticationEntryPoint")
    DelegatedAuthenticationEntryPoint authEntryPoint;

    private final AuthMiddlewareFilter authMiddlewareFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**", "/public/**", "/categories/**").permitAll() // public
                        .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                        // .requestMatchers("/admin/**").hasAnyAuthority("WRITE_PRODUCT") // admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/**").hasRole("USER") // user
                        //.requestMatchers("/users/get-sellers/**").hasRole("USER")
                        // .requestMatchers("/auth/user/logout").authenticated() // user
                        .anyRequest().authenticated()) // private
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(
                        authMiddlewareFilter, UsernamePasswordAuthenticationFilter.class
                )

                .logout((logout) -> logout.logoutUrl("/auth/user/logout/uri"))//"/auth/user/logout/uri"
                .exceptionHandling(
                        (exceptionHandling) ->
                                exceptionHandling
                                        // .accessDeniedHandler(DefaultExceptionHandler)
                                       // .accessDeniedPage("/errors/access-denied")
                                        .authenticationEntryPoint(authEntryPoint)
                )
                ;
        return httpSecurity.build();
    }




}
