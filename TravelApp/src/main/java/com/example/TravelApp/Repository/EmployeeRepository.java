package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT e FROM Employee e WHERE e.employeeId = ?1")
    Employee findByEmployeeId(String id);

    @Query(value = "SELECT t.position.name FROM Employee t WHERE t.employeeId = ?1")
    String findPositionName(String employeeId);
}