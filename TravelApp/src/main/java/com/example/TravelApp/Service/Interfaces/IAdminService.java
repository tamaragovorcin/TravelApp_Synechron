package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.Model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface IAdminService {

    Admin findByUsername(String username);
}
