package com.example.jwt.Jwtutils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component; // <--- import this

import java.util.Date;

@Component  // <--- tell Spring to manage this class
public class Createjwt {

    private final String secret = "MySuperSecretKeyForJWTGeneration12345";
    private final long expiration = 1000 * 60 * 60; // 1 hour

    public String generatejwt(String username, String role) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(algorithm);
    }
}
