package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.Model.Admin;
import com.example.TravelApp.Service.Interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminService adminService;

    @Override
    public Admin findByUsername(String username) {
        return adminService.findByUsername(username);
    }
}
