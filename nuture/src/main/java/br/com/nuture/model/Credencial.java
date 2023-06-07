package br.com.nuture.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record Credencial(String username, String password) {

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
    
}
