package com.ravid.clothes_marketplace.app.security;

import java.io.Serializable;
import java.time.Instant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;



@Component
public class JwtUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

    // 5 hours
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;
    private Algorithm algorithm;

    @PostConstruct
    private void init(){
        algorithm = Algorithm.HMAC256(secret);
    }

	//check if the token has expired
	public Boolean isTokenExpired(DecodedJWT jwt) {
		final Instant expiration = jwt.getExpiresAtAsInstant();
		return expiration.compareTo(Instant.now()) < 0;
	}

	//generate token for user
	public String generateToken(UserDetails userDetails) {

        String token = JWT.create()
                          .withSubject(userDetails.getUsername())
                          .withExpiresAt(Instant.now().plusSeconds(JWT_TOKEN_VALIDITY))
                          .withIssuedAt(Instant.now())
                          .sign(algorithm);
        
        return token;
	}

    public DecodedJWT decodeToken(String token) {
        return JWT.require(algorithm)
                  .build()
                  .verify(token);
    }

	//validate token
	public Boolean validateToken(DecodedJWT jwt, UserDetails userDetails) {
        return (jwt.getSubject().equals(userDetails.getUsername()) && !isTokenExpired(jwt));
	}
}