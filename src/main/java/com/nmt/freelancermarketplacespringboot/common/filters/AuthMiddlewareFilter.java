package com.nmt.freelancermarketplacespringboot.common.filters;

import com.nmt.freelancermarketplacespringboot.common.enums.ApiVersionEnum;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.auth.AuthExceptionMessages;
import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class AuthMiddlewareFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtServiceUtil jwtServiceUtil;

    @Autowired
    public AuthMiddlewareFilter(
            UserDetailsService userDetailsService,
            JwtServiceUtil jwtServiceUtil
    ) {
        this.userDetailsService = userDetailsService;
        this.jwtServiceUtil = jwtServiceUtil;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Kiểm tra version API từ header





        final String authHeader = request.getHeader("Authorization");


        /*
         * recommend in YouTube:
         * - Place the following code inside a try-catch block.
         * - Create an API endpoint list that allows access without requiring a token.
         */
        if (authHeader == null || authHeader.isBlank()) {
            request.setAttribute("message", AuthExceptionMessages.AUTH_MISSING_INFORMATION);
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
            request.setAttribute("message", ex.getMessage());
            throw new ServletException(AuthExceptionMessages.AUTH_ERROR.getMessage(), ex);
        }
    }


    /**
     * idea:
     *  in: "http://localhost:8888/majors/create"
     *  out: "http://localhost:8888/api/v1/majors/create"
     *  -> "http://localhost:8888" + "/api/v1" + "/majors/create"
     * @param request
     * @return
     */
    private HttpServletRequest CheckApiVersion (@NonNull HttpServletRequest request){
        String timestampVersion = request.getAttribute("X-GitHub-Api-Version").toString();
        // origin path từ yêu cầu
        // Biểu thức chính quy để lấy phần gốc của URL
        Pattern pattern = Pattern.compile("^(https?://[^/]+)");
        Matcher matcher = pattern.matcher(request.getRequestURL());
        String originalUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String baseUrl = request.getRequestURL().substring(
                0,
                request.getRequestURL().indexOf(request.getRequestURI())
        );

        return null;
    }


    private HttpServletRequest createWrappedRequest(@NonNull HttpServletRequest request, String newUri) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public String getRequestURI() {
                return newUri;
            }
            @Override
            public StringBuffer getRequestURL() {
                StringBuffer url = request.getRequestURL();
                int startPos = url.indexOf(request.getRequestURI());
                return url.replace(startPos, startPos + request.getRequestURI().length(), newUri);
            }
        };
    }
}
//  if (timestampVersion != null) {
//          if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V1.getTimestamp())) {
//          String newUri = contextPath + "/api/v1" + originalUri.substring(contextPath.length());
//          return createWrappedRequest(request, newUri);
//
//          } else if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V2.getTimestamp())) {
//          String newUri = contextPath + "/api/v2" + originalUri.substring(contextPath.length());
//          return createWrappedRequest(request, newUri);
//          }
//          }
//          return request;