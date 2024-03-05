//package com.nmt.freelancermarketplacespringboot.common.filters;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.constraints.NotNull;
//import org.antlr.v4.runtime.misc.Pair;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.antlr.v4.runtime.misc.Pair.*;
//
//
//@Component
//public class PublicFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        try
//        {
//            if(this.checkPublic(request)){
//                filterChain.doFilter(request, response);
//                return; // => đi vào Controller
//            }
//            else {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 403 Forbidden
//                response.getWriter().write("Access Forbidden");
//                response.getWriter().flush();
//                return;
//            }
//        }catch (Exception ex){
//            throw new ServletException("Error in public filter", ex);
//        }
//    }
//
//
//    private boolean checkPublic(@NotNull HttpServletRequest req) {
//        final Map<String, String> notTokenEndpoints = new HashMap<>();
//        notTokenEndpoints.put("/auth/user/login", "POST");
//        notTokenEndpoints.put("/auth/user/register", "POST");
//
//        String requestUri = req.getRequestURI();
//        String requestMethod = req.getMethod();
//
//        for (Map.Entry<String, String> entry : notTokenEndpoints.entrySet()) {
//            if (entry.getKey().equals(requestUri) && entry.getValue().equals(requestMethod)) {
//                return true; // It's a public endpoint, allow access without a token
//            }
//        }
//
//        return false;
//    }
//}
//
//
//
//
//
//
////    private boolean checkPublic(@NotNull HttpServletRequest req) {
////        final List<Pair<String, String>> notToken = Arrays.asList(
////                Pair.of(String.format("%s/login", ""), "POST"),
////                Pair.of(String.format("%s/register", ""), "POST")
////        );
////        return false;
////    }
