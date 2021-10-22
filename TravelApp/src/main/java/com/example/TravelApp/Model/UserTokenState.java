package com.example.TravelApp.Model;

import java.util.List;

public class UserTokenState  {

    private String accessToken;
    private Long expiresIn;
    private List<String> roles;
    private String expires;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.roles = null;
        this.expires = null;
    }

    public UserTokenState(String accessToken, String expires, List<String> roles) {
        this.accessToken = accessToken;
        this.roles = roles;
        this.expires = expires;

    }

    public UserTokenState(String accessToken, String expires) {
        this.accessToken = accessToken;
        this.expires = expires;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}