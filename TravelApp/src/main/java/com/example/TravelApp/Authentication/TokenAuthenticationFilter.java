package com.example.TravelApp.Authentication;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

//Filter that will intercept client requests to the server
//Except the paths that are specified in WebSecurityConfig.configure(WebSecurity web)
public class TokenAuthenticationFilter extends OncePerRequestFilter{

    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String email;
        String authToken = tokenUtils.getToken(request);
        //Get email from the token
        if (authToken != null) {
            email = tokenUtils.getUsernameFromToken(authToken);
            //Get user by username(email)
            if (email != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                //Check if forwarded token is valid
                if (tokenUtils.validateToken(authToken, userDetails)) {
                    //Create authentication
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        //Forward the request to the next filter
        chain.doFilter(request, response);
    }
}
