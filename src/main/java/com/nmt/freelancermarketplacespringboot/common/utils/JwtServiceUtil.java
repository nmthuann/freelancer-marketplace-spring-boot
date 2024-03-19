package com.nmt.freelancermarketplacespringboot.common.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceUtil {
    // @Value("${jwt.secret-key}")
    private static final String jwtSecretKey = "873f47869c454e32b09a992174b280631234ab77720";

    // @Value("${jwt.refresh-secret-key}")
    private static final String refreshJwtSecretKey = "873f47869c454e32b09a992174b880731234ab77720";

    @Value("${jwt.access.token.expiration}")
    private Long accessTokenExpiration;

    @Value("${refresh.jwt.access.token.expiration}")
    private Long refreshTokenExpiration;


    public Tokens getTokens(Payload payload) {
        try {
            String jwt = generateToken(payload, jwtSecretKey, accessTokenExpiration);
            String refreshJwt = generateToken(payload, refreshJwtSecretKey, refreshTokenExpiration);
            return new Tokens(jwt, refreshJwt);
        } catch (Exception ex) {
            System.out.println("getTokens" + ex.getMessage());
            throw ex;
        }
    }

    private String generateToken(Payload payload, String secretKey, Long expiration) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("payload", payload);

            return Jwts.builder()
                    .subject(payload.sub())
                    .claims(claims)
                    .expiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                    .compact();
        } catch (InvalidClaimException e) {
            throw new RuntimeException("Failed to generate token", e);
        }
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(convertStringToSecretKey())
                    .build()
                    .parseSignedClaims(token).getPayload();
        }
        catch (JwtException ex ){
            throw new JwtException(ex.getMessage());
        }
    }

    private SecretKey convertStringToSecretKey(){
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {

        try {

            //  refactor code here
            return extractClaim(token, claims -> {
                LinkedHashMap<String, Object> payloadMap = claims.get("payload", LinkedHashMap.class);
                Payload payload = new ObjectMapper().convertValue(payloadMap, Payload.class);
                return payload.email();
            });
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new RuntimeException("Failed to extract email from token", e);
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

}

//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
