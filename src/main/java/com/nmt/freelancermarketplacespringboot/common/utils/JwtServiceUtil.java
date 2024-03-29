package com.nmt.freelancermarketplacespringboot.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users.JwtExceptionMessages;
import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtServiceUtil {

    String jwtSecretKey;

    String refreshJwtSecretKey;
    Long accessTokenExpiration;
    Long refreshTokenExpiration;

    @Autowired
    public JwtServiceUtil(
            @Value("${jwt.secret-key}") String jwtSecretKey,
            @Value("${jwt.refresh-secret-key}") String refreshJwtSecretKey,
            @Value("${jwt.access.token.expiration}") Long accessTokenExpiration,
            @Value("${refresh.jwt.access.token.expiration}") Long refreshTokenExpiration
    ) {
        this.jwtSecretKey = jwtSecretKey;
        this.refreshJwtSecretKey = refreshJwtSecretKey;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
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

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
//        try {
//            return extractExpiration(token).before(new Date());
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to extract expiration from token", e);
//        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    /**
     * @param payload {String email, String role, String sub}
     * @param secretKey String
     * @param expiration Long
     * @return EX: String jwt.builder().subject("Joe").signWith(key).compact();
     * Note: signWith -> 'signWith(io.jsonwebtoken.SignatureAlgorithm, java.security.Key)' is deprecated
     */
    private String generateToken(Payload payload, String secretKey, Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("payload", payload);

        return Jwts.builder()
                .subject(payload.sub())
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                .compact();
    }


    /**
     * @param payload {String email, String role, String sub}
     * @return Tokens: {String accessToken,  String refreshToken}
     */
    public Tokens getTokens(Payload payload) {
        String accessToken = generateToken(payload, jwtSecretKey, accessTokenExpiration);
        String refreshToken = generateToken(payload, refreshJwtSecretKey, refreshTokenExpiration);
        return new Tokens(accessToken, refreshToken);
    }


    /**
     * check Token is valid ?
     * @param token EX: "eyJhbGciOiJIUzI1NiJ9...."
     * @param userDetails Note: "spring security manager!"
     * @return boolean type
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }


    /**
     * extractClaim -> token
     * @param token "eyJhbGciOiJIUzI1NiJ9...."
     * @return payload.email()
     */
    public String extractUsername(String token) {
        try {
            return extractClaim(token, claims -> {
                Payload payload =
                        new ObjectMapper()
                                .convertValue(claims.get("payload"), Payload.class);
                return payload.email();
            });
        } catch (ExpiredJwtException
                 | UnsupportedJwtException
                 | MalformedJwtException
                 | IllegalArgumentException e
        ) {
            throw new RuntimeException(JwtExceptionMessages.EXTRACT_USERNAME_FAILED.getMessage(), e);
        }
    }
}





//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

//            return extractClaim(token, claims -> {
//                LinkedHashMap<String, Object> payloadMap = claims.get("payload", LinkedHashMap.class);
//                Payload payload = new ObjectMapper().convertValue(payloadMap, Payload.class);
//                return payload.email();
//            });

//    @Value("${jwt.secret-key}")
//    private final String jwtSecretKey; //= "873f47869c454e32b09a992174b280631234ab77720" static final

//    @Value("${jwt.refresh-secret-key}")
//    //private static final String refreshJwtSecretKey = "873f47869c454e32b09a992174b880731234ab77720";
//    private final String refreshJwtSecretKey ;
//
//    @Value("${jwt.access.token.expiration}")
//    private final Long accessTokenExpiration;
//
//    @Value("${refresh.jwt.access.token.expiration}")
//    private final Long refreshTokenExpiration;
//
//
//    public JwtServiceUtil(
//            String jwtSecretKey,
//            String refreshJwtSecretKey,
//            Long accessTokenExpiration,
//            Long refreshTokenExpiration
//    ) {
//        this.jwtSecretKey = jwtSecretKey;
//        this.refreshJwtSecretKey = refreshJwtSecretKey;
//        this.accessTokenExpiration = accessTokenExpiration;
//        this.refreshTokenExpiration = refreshTokenExpiration;
//    }

