package br.com.nuture.service;

import br.com.nuture.model.Credencial;
import br.com.nuture.model.Token;
import br.com.nuture.model.User;
import br.com.nuture.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    UserRepository repository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(@Valid Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withSubject(credencial.username())
                .withIssuer("nuture")
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign(alg);
        return new Token(token, "JWT", "Bearer");
    }

    public User getValidateUser(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var username = JWT.require(alg)
                .withIssuer("nuture")
                .build()
                .verify(token)
                .getSubject();
        return repository.findByEmail(username)
                .orElseThrow(() -> new JWTVerificationException("user invalid"));
    }

}
