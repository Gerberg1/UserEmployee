package com.example.useremployee.controller;

import com.example.useremployee.model.Employee;
import com.example.useremployee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }


    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> putEmployee(@RequestBody Employee employee){
        Optional<Employee> orgEmployee = employeeRepository.findById(employee.getId());
        if (orgEmployee.isPresent()){
            employeeRepository.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Employee(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/employee/name/{name}")
    public ResponseEntity<Employee> findEmployeeByName(@PathVariable String name) {
        Employee employee = employeeRepository.findEmployeeByName(name);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }



    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> findStudentByID(@PathVariable int id){
        Employee employee = employeeRepository.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        Optional<Employee> orgEmployee = employeeRepository.findById(id);
        if (orgEmployee.isPresent()){
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee deleted");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }
}

