//package com.example.clickintegration.service;
//
//import com.example.clickintegration.entity.UserEntity;
//import com.example.clickintegration.exception.RecordNotFound;
//import com.example.clickintegration.jwt.JwtUtils;
//import com.example.clickintegration.repository.UserRepository;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class TokenService {
//    private final UserRepository userRepository;
//
//    public void createAccessToken(HttpServletResponse httpServletResponse, String refreshToken) {
//        Claims claims = JwtUtils.extractRefreshToken(refreshToken);
//        if (claims == null) {
//            throw new RecordNotFound("refresh token time expiry");
//        }
//        String username = claims.getSubject();
//        UserEntity userEntity = userRepository
//                .findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
//        String accessToken = JwtUtils.generateToken(userEntity);
//
//        httpServletResponse.setHeader("token", accessToken);
//    }
//}
