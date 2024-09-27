package com.homework.Spring_Mockito.controllers;

import com.homework.Spring_Mockito.Employee;
import com.homework.Spring_Mockito.EmployeeServiceImpl;
import com.homework.Spring_Mockito.exception.EmployeeAlreadyAddedException;
import com.homework.Spring_Mockito.exception.EmployeeNotFoundException;
import com.homework.Spring_Mockito.exception.EmployeeStorageIsFullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping(path = "/add")
    public ResponseEntity<String> addEmployee(@RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("salary") Integer salary,
                                              @RequestParam("department") Integer department) {
        try {
            Employee employee = employeeServiceImpl.addEmployee(firstName, lastName, salary, department);
            return ResponseEntity.ok("Сотрудник " + employee.getFullName() + " добавлен");
        } catch (EmployeeStorageIsFullException e) {
            throw e;
        } catch (EmployeeAlreadyAddedException e) {
            throw e;
        }
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        try {
            return employeeServiceImpl.removeEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName) {
        try {
            employeeServiceImpl.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Сотрудник " + firstName + " " + lastName + " не найден");
            throw e;
        }
        return employeeServiceImpl.findEmployee(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return employeeServiceImpl.findAll();
    }
}