package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "SELECT a FROM Admin a WHERE a.username = ?1")
    Admin findByUsername(String username);
}
