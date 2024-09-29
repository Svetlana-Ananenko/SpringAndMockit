package com.homework.Spring_Mockito.service;

import com.homework.Spring_Mockito.Employee;
import com.homework.Spring_Mockito.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> employeesDepartment(Integer department) {
        return employeeService.findAll()
                .stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getDepartment() == department)
                .toList();
    }

    @Override
    public int getEmployeeWithMaxSalary(Integer departmentId) {
        return employeesDepartment(departmentId)
                .stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public int getEmployeeWithMinSalary(Integer departmentId) {
        return employeesDepartment(departmentId)
                .stream()
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(EmployeeNotFoundException ::new);
    }

    @Override
    public int getSalariesSumByDepartment(Integer departmentId) {
        return employeesDepartment(departmentId)
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> allEmployeesDepartments() {
        return employeeService.findAll()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}