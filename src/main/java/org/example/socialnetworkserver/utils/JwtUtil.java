package org.example.socialnetworkserver.utils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

//    private static final String SECRET_KEY = "your-256-bit-secret-key-here";
//    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private static final long EXPIRATION_TIME = (1000 * 60 * 60 ) * 8; // 8 hours in milliseconds

    public static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate JWT Token
    public static String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // Validate JWT Token
    public static boolean validateToken(String token) {

        try {
             Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
             return true;
        } catch (Exception e) {
            return false;
        }
    }



    public static Claims getParsedToken(String token) {
        try {
            // Parse the token and get the payload
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims;
        } catch (JwtException e) {
            System.err.println("Invalid token: " + e.getMessage());
        }
        return null;
    }

    public static String getTokensValue(String token){
        Claims parsedToken = getParsedToken(token);
        return parsedToken!= null ? parsedToken.getSubject() : null;
    }


}

