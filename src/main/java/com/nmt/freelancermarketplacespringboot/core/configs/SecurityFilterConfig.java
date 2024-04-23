package com.nmt.freelancermarketplacespringboot.core.configs;


import com.nmt.freelancermarketplacespringboot.common.filters.AuthMiddlewareFilter;
import com.nmt.freelancermarketplacespringboot.components.security.DelegatedAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


/**
 * Khoi tao dau tien
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfig {

    @Autowired
    DelegatedAuthenticationEntryPoint authEntryPoint;

    private final AuthMiddlewareFilter authMiddlewareFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/auth/**",
                                "/public/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjar/**",
                                "/javainuse-openapi/**",
                                "/configuration-ui",
                                "/configuration-security",
                                "/swagger-ui.html"
//                                "/actuator/**" -> lib handled
                        ).permitAll() // public
                        .requestMatchers(
                                HttpMethod.GET, "/categories/**"
                        ).permitAll() //"/users/**"
                        .requestMatchers(
                                "/admin/**",
                                "/categories/**",
                                "/majors/**"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "/users/**",
                                "/posts/**",
                                "/reviews/**"
                        ).hasRole("USER") // user
                        // .requestMatchers("/admin/**").hasAnyAuthority("WRITE_PRODUCT") // admin
                        // .requestMatchers("/auth/user/logout").authenticated() // user
                        .anyRequest().authenticated()) // private
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                    .addFilterBefore(authMiddlewareFilter, UsernamePasswordAuthenticationFilter.class)

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



