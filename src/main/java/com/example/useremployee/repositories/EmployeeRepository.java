package com.example.useremployee.repositories;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByName(String name);

    Employee findEmployeeById(int id);
}
