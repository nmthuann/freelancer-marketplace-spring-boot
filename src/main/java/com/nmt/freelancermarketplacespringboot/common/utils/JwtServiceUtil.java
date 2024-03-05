package com.nmt.freelancermarketplacespringboot.common.utils;

import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceUtil {
    // @Value("${jwt.secret-key}")
    private static final String jwtSecretKey = "873f4786-9c45-4e32-b09a-992174b28063";

    // @Value("${jwt.refresh-secret-key}")
    private static final String refreshJwtSecretKey = "873f4786-9c45-4e32-b09a-992174b88073";

    @Value("${jwt.access.token.expiration}")
    private Long accessTokenExpiration;

    @Value("${refresh.jwt.access.token.expiration}")
    private Long refreshTokenExpiration;



    public Tokens getTokens(Payload payload) {

        try {
            String jwt = generateToken(payload, jwtSecretKey, accessTokenExpiration);
            System.out.println("jwt:" + jwt);
            String refreshJwt = generateToken(payload, refreshJwtSecretKey, refreshTokenExpiration);
            System.out.println("refreshJwt:" + refreshJwt);
            return new Tokens(jwt, refreshJwt);
        } catch (Exception ex) {
            ex.printStackTrace(); // Hoặc log thông báo lỗi
            System.out.println("getTokens" + ex.getMessage());
            // return null; // Hoặc xử lý khác tùy thuộc vào yêu cầu của bạn
            throw ex;
        }
    }

    private String generateToken(Payload payload, String secretKey, Long expiration) {



//        Map<String, Object> claims = new HashMap<>();
//        claims.put("payload", payload);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
//                .signWith(SignatureAlgorithm.HS256, secretKey) //HS512
//                .compact();
        // SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
       //  SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("payload", payload);

            return Jwts.builder()
                    .setSubject(payload.email())
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(SignatureAlgorithm.HS256, Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .compact();
        } catch (InvalidClaimException e) {
            // Bắt lỗi và xử lý nếu có vấn đề khi tạo token
            e.printStackTrace(); // Hoặc log thông báo lỗi
            throw new RuntimeException("Failed to generate token", e); // Hoặc xử lý khác tùy vào yêu cầu của bạn
        }
    }

    private Claims extractAllClaims(String token){
        Jwt<?,?> jwt;
        try {
//            jwt =
////                    Jwts.parser()
////                    .keyLocator((Locator<Key>) jwtSecretKey)
////                    .build();
            Jwts.parser()

                    .verifyWith(jwtSecretKey) // <----

                    .build()
                    .parseSignedClaims(token);
        }
        catch (JwtException  ex ){
            System.out.println(ex.getMessage());
        }
    }

    private Key getSignInKey(){
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public String extractUsername(String token) {

        return "";
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        return true;
    }


//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }

//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
//    }


//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }



}