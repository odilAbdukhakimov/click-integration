//package com.example.clickintegration.controller.filter;
//
//import com.example.clickintegration.jwt.JwtUtils;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//public class JwtTokenFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorization = request.getHeader("Authorization");
//        HttpSession session = request.getSession();
//        System.out.println(session);
//
//        if (authorization == null || !authorization.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String accessToken = authorization.replace("Bearer ", "");
//        Claims claims = JwtUtils.extractToken(accessToken);
//
//        if (claims == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        String username = claims.getSubject();
//        List<LinkedHashMap<String, String>> authorities = (List<LinkedHashMap<String, String>>) claims.get("authorities");
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(
//                        username,
//                        null,
//                        getAuthorities(authorities)
//                );
////        authentication.setDetails(new WebAuthenticationDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//
//    }
//
//    private List<SimpleGrantedAuthority> getAuthorities(List<LinkedHashMap<String, String>> authorities) {
//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//        authorities.forEach((map) -> {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                    authorityList.add(new SimpleGrantedAuthority(entry.getValue()));
//            }
//        });
//        return authorityList;
//    }
//
//}
