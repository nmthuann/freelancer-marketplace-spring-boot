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

import java.util.List;

@Component
//@RequiredArgsConstructor
public class AuthMiddlewareFilter extends OncePerRequestFilter {

    @Autowired
    JwtServiceUtil jwtServiceUtil;

    @Autowired
    UserDetailsService  authService;



    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String email;
        final String token;

        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);
        // userEmail = jwtUtils.extractUsername(jwtToken);
        email = this.jwtServiceUtil.extractUsername(token);
        try {


            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //  UserDetails userDetails = (UserDetails) this.accountService.getOneById(email); // bug in here???

                UserDetails userDetails = authService.loadUserByUsername(email);
                if (jwtServiceUtil.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
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
            // }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
