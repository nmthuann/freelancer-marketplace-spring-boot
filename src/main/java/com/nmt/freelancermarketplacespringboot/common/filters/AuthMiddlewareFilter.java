package com.nmt.freelancermarketplacespringboot.common.filters;

import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthMiddlewareFilter extends OncePerRequestFilter {

    @Autowired
    JwtServiceUtil jwtServiceUtil;

    @Autowired
    IAccountService accountService;

    private List<String> publicEndpoints = Arrays.asList(
            "/auth/user/login",
            "/auth/user/register",
            "/authMethod/{id}"
    );

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String email;

        try {

            // isPublic: filter the public request.
            if (this.isPublic(request)){
                filterChain.doFilter(request,response);
            }
            else {
                // handle the header request
                String token = getToken(request);

                email = this.jwtServiceUtil.extractUsername(token);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = (UserDetails) this.accountService.getOneById(email); // bug in here???
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
                filterChain.doFilter(request,response);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }

    private boolean isPublic(HttpServletRequest request) {

        return !request.getRequestURI().startsWith("/auth/user");
    }

    private String getToken (HttpServletRequest request) {

        final String header = request.getHeader("Authorization");
        final String token;


        if (header == null || !header.startsWith("Bearer ")){
            // throw HttpClientErrorException.Forbidden;
            throw new SecurityException("Unauthorized");
        }
        else {
            token = header.substring(7);

        }

        return token;
    }
}
