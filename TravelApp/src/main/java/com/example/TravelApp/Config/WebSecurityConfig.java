package com.example.TravelApp.Config;

import com.example.TravelApp.Authentication.RestAuthenticationEntryPoint;
import com.example.TravelApp.Authentication.TokenAuthenticationFilter;
import com.example.TravelApp.Authentication.TokenUtils;
import com.example.TravelApp.Service.Implementations.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
//Enable annotation "@Pre*" & "@Post*" which are going to check authorization for every method access
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Service which is used for reading data about application users
    @Autowired
    private CustomUserDetailsServiceImpl jwtUserDetailsService;

    //Handler for returning 401 when client with incorrect username and password tries to access the resource
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    //We register an authentication manager who is going to do user authentication for us
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private TokenUtils tokenUtils;

    //ACCES TO SPECIFIC URL'S
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //REST application
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                //Send a 401 error for every unathorized request
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

                //Allow all users to access defined paths

                //For every other request the user must be authenticated
                .authorizeRequests().antMatchers("/login").permitAll().and()

                //For development purposes include configuration for CORS from the WebConfig class
                .cors().and()

                //Insert custom filter TokenAuthenticationFilter to check JWT tokens instead of pure username and password (performed by BasicAuthenticationFilter)
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
                        BasicAuthenticationFilter.class);
        //Due to simplicity of the example
        http.csrf().disable();
    }

    //GENERAL SECURITY OF THE APPLICATION
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js");

    }
}
