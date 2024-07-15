package com.fancy.internsys.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "temp";
    private static final int EXPIRE_TIME = 1000*60*60*24*3; //3days

    public String generateToken(String uuid,String username,String role){ //需要role来鉴权
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("typ","jwt")
                .setHeaderParam("alg","RS256")
                .claim("username",username)
                .claim("role",role)
                .claim("uuid",uuid)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .signWith(SignatureAlgorithm.RS256,SECRET_KEY)
                .compact();
        return jwtToken;
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token){
        return (String) getClaims(token).get("username");
    }

    public String extractRole(String token){
        return (String) getClaims(token).get("role");
    }

    public String extractUuid(String token){
        return (String) getClaims(token).get("uuid");
    }

    public boolean validateToken(String token,String role){
        return (role.equals(extractRole(token)) && !isTokenExpired(token));
    }
}
