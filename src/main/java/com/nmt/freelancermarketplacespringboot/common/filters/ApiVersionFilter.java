package com.nmt.freelancermarketplacespringboot.common.filters;

import com.nmt.freelancermarketplacespringboot.common.enums.ApiVersionEnum;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.auth.AuthExceptionMessages;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ApiVersionFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String timestampVersion = request.getHeader("X-GitHub-Api-Version");

        try {
            if (timestampVersion != null) {
                String requestURI = request.getRequestURI();
                String newURI = null;

                if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V1.getTimestamp())) {
                    System.out.println("current: " + request.getRequestURI());
                    newURI = "/api/v1" + requestURI;

                } else if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V2.getTimestamp())) {
                    newURI = "/api/v2" + requestURI;
                } else {
                    request.setAttribute("message", AuthExceptionMessages.AUTH_INVALID_API_VERSION.getMessage());
                    throw new ServletException(AuthExceptionMessages.AUTH_INVALID_API_VERSION.getMessage());
                }

                if (newURI != null) {
                    String finalNewURI = newURI;
                    HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(request) {
                        @Override
                        public String getRequestURI() {
                            return finalNewURI;
                        }

                        @Override
                        public String getServletPath() {
                            return finalNewURI;
                        }
                    };
                    System.out.println("new: " + wrappedRequest.getRequestURI());
                    filterChain.doFilter(wrappedRequest, response);
                    // return;
                }
            } else {
                request.setAttribute("message", AuthExceptionMessages.AUTH_MISSING_API_VERSION.getMessage());
                throw new ServletException(AuthExceptionMessages.AUTH_MISSING_API_VERSION.getMessage());
            }

        } catch (ServletException ex) {
            throw new ServletException(ex.getMessage());
        }
    }
}



// else {
//        // Nếu không có tiêu đề hoặc phiên bản không hợp lệ, tiếp tục chuỗi bộ lọc bình thường
//        request.setAttribute("message",
//                timestampVersion == null ?
//                        AuthExceptionMessages.AUTH_MISSING_API_VERSION :
//                        AuthExceptionMessages.AUTH_INVALID_API_VERSION
//        );
//        filterChain.doFilter(request, response);
//    }
//        Pattern pattern = Pattern.compile("^(https?://[^/]+)");
//        Matcher matcher = pattern.matcher(request.getRequestURL());
//        String baseURL = matcher.group(1);
//if (timestampVersion != null) {
//
//        if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V1.getTimestamp())) {
//        String requestURI = request.getRequestURI();
//        String newURI = "/api/v1" + requestURI;
//        RequestDispatcher dispatcher = request.getRequestDispatcher(newURI);
//        dispatcher.forward(request, response);
//        } else if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V2.getTimestamp())) {
//        String requestURI = request.getRequestURI();
//        String newURI = "/api/v2" + requestURI;
//        RequestDispatcher dispatcher = request.getRequestDispatcher(newURI);
//        dispatcher.forward(request, response);
//        }
//        else {
//        request.setAttribute("message", AuthExceptionMessages.AUTH_INVALID_API_VERSION);
//        filterChain.doFilter(request, response);
//        }
//        } else{
//        request.setAttribute("message", AuthExceptionMessages.AUTH_MISSING_API_VERSION);
//        filterChain.doFilter(request, response);
//        }
//        if (timestampVersion != null) {
//            if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V1.getTimestamp())) {
//                String requestURI = request.getRequestURI();
//                String newURI = "/api/v1" + requestURI;
//                RequestDispatcher dispatcher = request.getRequestDispatcher(newURI);
//                dispatcher.forward(request, response);
//            } else if (timestampVersion.equals(ApiVersionEnum.API_VERSION_V2.getTimestamp())) {
//                String requestURI = request.getRequestURI();
//                String newURI = "/api/v2" + requestURI;
//                RequestDispatcher dispatcher = request.getRequestDispatcher(newURI);
//                dispatcher.forward(request, response);
//            }
//            else {
//                request.setAttribute("message", AuthExceptionMessages.AUTH_INVALID_API_VERSION);
//                filterChain.doFilter(request, response);
//            }
//        } else{
//            request.setAttribute("message", AuthExceptionMessages.AUTH_MISSING_API_VERSION);
//            filterChain.doFilter(request, response);
//        }