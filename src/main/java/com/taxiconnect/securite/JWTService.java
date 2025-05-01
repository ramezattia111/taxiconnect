package com.taxiconnect.securite;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Component
public class JWTService {

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(@NonNull UserDetails userEntity)
    {
        String email = userEntity.getUsername();
        Date currentData = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentData)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    public boolean validateToken(String token) {
        try{
            Claims claims = extractAllClaims(token);
        }
        catch (ExpiredJwtException ex)
        {
            throw new ExpiredJwtException(null,null,"Token has expired. Please log in again.", ex);
        }

        return true;
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpirationDate(token).before(new Date());
    }
    public boolean isTokenValid(String token, @NotNull UserDetails userEntity)
    {
        final String email = extractEmailFromJwt(token);
        return email.equals(userEntity.getUsername()) && !isTokenExpired(token);
    }
    public <T> T extractClaim(String token , @NotNull Function<Claims,T> claimResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public Claims extractAllClaims(String token)
    {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new ExpiredJwtException(null,null,"Token has expired. Please log in again.");
        }
    }

    public Date extractExpirationDate(String token)
    {
        return extractClaim(token,Claims::getExpiration);
    }

    public Date extractIssuedAtDate(String token)
    {
        return extractClaim(token , Claims::getIssuedAt);
    }
    public String extractEmailFromJwt(String token)
    {
        return extractClaim(token , Claims::getSubject);
    }

}
