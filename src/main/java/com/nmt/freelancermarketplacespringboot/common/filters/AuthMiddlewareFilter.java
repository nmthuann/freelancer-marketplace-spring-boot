package com.nmt.freelancermarketplacespringboot.common.filters;

import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
public class AuthMiddlewareFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtServiceUtil jwtServiceUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        /*
         * recommend in YouTube:
         * - Place the following code inside a try-catch block.
         * - Create an API endpoint list that allows access without requiring a token.
         */
        if (authHeader == null || authHeader.isBlank()) {
            // response.setStatus(403);
            request.setAttribute("message", "Missing authorization information");
            filterChain.doFilter(request, response);
            return;
        }
        // throw new ServletException("Missing authorization information");


        /*
         * authHeader != null
         * Ex: "Bearer eyJhbGciOiJIUzI1NiJ9..." -> beginIndex: 7
         */

        try {
            final String token = authHeader.substring(7);
            final String email = this.jwtServiceUtil.extractUsername(token);
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                if (jwtServiceUtil.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken (
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            System.out.println("AuthMiddlewareFilter" + ex.getMessage());
            // logger.error("An error occurred in AuthMiddlewareFilter", ex);
            request.setAttribute("message", ex.getMessage());
            throw new ServletException("An error occurred while processing authentication", ex);
        }

    }
}
