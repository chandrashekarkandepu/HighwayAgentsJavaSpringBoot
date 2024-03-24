package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.models.Agency;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.repositories.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY= "13029c1e462c911e59328a76fc08a7dffbf8bfbf3fd1263bb6613fc583c70da3";

    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public boolean isValid(String token, Contractor contractor){
        String username = extractUsername(token);

        boolean isValidToken = tokenRepository.findByToken(token).map(
                t->!t.isLoggedOut()
        ).orElse(false);

//        System.out.println(username + " "+contractor.getEmailId()+" "+isTokenExpired(token));
        return (username.equals(contractor.getEmailId())) && !isTokenExpired(token) && isValidToken;

    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims=extractallClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractallClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(Contractor contractor){
        String token = Jwts
                .builder()
                .subject(contractor.getEmailId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
