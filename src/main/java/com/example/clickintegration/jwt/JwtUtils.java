package com.example.clickintegration.jwt;

import com.example.clickintegration.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class JwtUtils {

    public static String generateToken(
            UserEntity userDetails
    ) {
        System.out.println(userDetails.getRoles().get(0));
        return Jwts.builder()
                .claim("role",userDetails.getRoles().get(0))
                .claim("username",userDetails.getUsername())
                .claim("user_id",userDetails.getId())
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 5)))
                .signWith(SignatureAlgorithm.HS256, "token")
                .setHeaderParam("typ","JWT")
                .compact();
    }

    public static Claims extractToken(String token){
        try {
            return Jwts.parser().setSigningKey("access_token").parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}
