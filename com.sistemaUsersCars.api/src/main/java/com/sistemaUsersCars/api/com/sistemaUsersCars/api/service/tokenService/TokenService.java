package com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.tokenService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
     private String secret;

     public String gerarToken(Users user){
         try{
           var algoritimo =  Algorithm.HMAC256(user.getPassword());
           return JWT.create()
                   .withIssuer("sistemaUsersCarsApi")
                   .withSubject(user.getLogin())
                   .withExpiresAt(dataExpiracao())
                   .sign(algoritimo);
         }catch (JWTCreationException exception){
             throw new RuntimeException("erro ao gerar token Jwt", exception);
         }
     }
     public String subject(String tokenJwt){
         try {
             var algoritmo = Algorithm.HMAC256(secret);
             return JWT.require(algoritmo)
                     .withIssuer("sistemaUsersCarsApi")
                     .build()
                     .verify(tokenJwt)
                     .getSubject();
         }catch (JWTVerificationException exception){
             throw new RuntimeException("Token JWT inválido ou expirado!");
         }
     }

    private Instant dataExpiracao() {
         return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
