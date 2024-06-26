package com.nmt.freelancermarketplacespringboot.core.configs;


import com.nmt.freelancermarketplacespringboot.common.filters.ApiVersionFilter;
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
 * initialization in the program ...
 */
@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityFilterConfig {

    private final DelegatedAuthenticationEntryPoint authEntryPoint;
    private final AuthenticationProvider authenticationProvider;
    private final ApiVersionFilter apiVersionFilter;
    private final AuthMiddlewareFilter authMiddlewareFilter;

    @Autowired
    public SecurityFilterConfig (
            DelegatedAuthenticationEntryPoint authEntryPoint,
            AuthenticationProvider authenticationProvider,
            ApiVersionFilter apiVersionFilter,
            AuthMiddlewareFilter authMiddlewareFilter
    ) {
        this.authEntryPoint = authEntryPoint;
        this.authenticationProvider = authenticationProvider;
        this.authMiddlewareFilter = authMiddlewareFilter;
        this.apiVersionFilter = apiVersionFilter;
    }


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
                                "/swagger-ui.html",
                                "/actuator/**", //-> lib handled
                                "/api/v1/**"
                        ).permitAll() // public
                        .requestMatchers(
                                HttpMethod.GET,
                                "/categories/**",
                                "/posts/**"
                        ).permitAll() //"/users/**"
                        .requestMatchers(
                                "/admin/**",
                                "/categories/**",
                                "/majors/**"
                                //"/api/v1/**"
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
                    .addFilterBefore(apiVersionFilter, AuthMiddlewareFilter.class)
                .logout((logout) -> logout.logoutUrl("/auth/user/logout/uri"))//"/auth/user/logout/uri"
                .exceptionHandling(
                        (exceptionHandling) ->
                                exceptionHandling
                                        // .accessDeniedHandler(DefaultExceptionHandler)
                                       // .accessDeniedPage("/errors/access-denied")
                                        .authenticationEntryPoint(authEntryPoint)
                );
        return httpSecurity.build();
    }
}



