package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.Model.Admin;
import com.example.TravelApp.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public AdminRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin administrator = administratorRepository.findByUsername(s);
        if(administrator == null) {
            throw new UsernameNotFoundException(String.format("No administrator found with username '%s'.", administratorRepository.findByUsername(s)));
        }else {
            return (UserDetails) administrator;
        }
    }
}
