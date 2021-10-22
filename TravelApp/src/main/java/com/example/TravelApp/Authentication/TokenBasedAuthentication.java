package com.example.TravelApp.Authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

//Override authentication class
public class TokenBasedAuthentication extends AbstractAuthenticationToken{

    private static final long serialVersionUID = 1L;

    private String token;
    private final UserDetails principal;

    public TokenBasedAuthentication(UserDetails principal) {
        super(principal.getAuthorities());
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
