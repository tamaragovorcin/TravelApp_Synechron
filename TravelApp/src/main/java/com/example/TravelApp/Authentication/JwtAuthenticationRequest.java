package com.example.TravelApp.Authentication;

//DTO for login
public class JwtAuthenticationRequest {

        private String username;
        private String password;
        private Boolean keepMeLoggedIn;

        public JwtAuthenticationRequest() {

        }

        public JwtAuthenticationRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    public Boolean getKeepMeLoggedIn() {
        return keepMeLoggedIn;
    }

    public void setKeepMeLoggedIn(Boolean keepMeLoggedIn) {
        this.keepMeLoggedIn = keepMeLoggedIn;
    }
}

