package com.nmt.freelancermarketplacespringboot.core.configs;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        try {
            http.authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/auth/user/login").permitAll()
//                    .anyRequest().authenticated()
            ).httpBasic(httpSecurityHttpBasicConfigurer -> {});
        }catch (Exception ex ){
            System.out.println("An error occurred while configuring security: " + ex.getMessage());
        }

        return http.build();
    }
}








// http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers("/**").authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();

//        http.addFilterBefore(new PublicFilter(), BasicAuthenticationFilter.class.class)
////                .csrf(httpSecurityCsrfConfigurer -> {})
//                .authorizeHttpRequests(requests -> {
//                    requests.requestMatchers("/auth/user/login").permitAll()// Permit access to /auth/user/login without authentication
//                            .requestMatchers("/auth/user/register").permitAll(); // Permit access to /auth/user/register without authentication
//                            // .anyRequest().authenticated();
//                });

//        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
//                        expressionInterceptUrlRegistry.requestMatchers("/auth/user/login").permitAll()
//                                .anyRequest().authenticated())
//                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.authenticationEntryPoint(authenticationEntryPoint));
//        http.addFilterBefore(new PublicFilter(), BasicAuthenticationFilter.class);

//        http.authorizeHttpRequests()
//                addFilterBefore(
//                new PublicFilter(), BasicAuthenticationFilter.class);
