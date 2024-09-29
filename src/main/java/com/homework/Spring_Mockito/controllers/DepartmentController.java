package com.homework.Spring_Mockito.controllers;

import com.homework.Spring_Mockito.Employee;
import com.homework.Spring_Mockito.service.DepartmentServiceImpl;
import com.homework.Spring_Mockito.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getEmployeesByDepartment(@PathVariable Integer id) {
        return departmentService.employeesDepartment(id);
    }

    @GetMapping("/{id}/max-salary")
    public int maxSalaryDepartment(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMaxSalary(id);
    }

    @GetMapping("/{id}/min-salary")
    public int minSalaryDepartment(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMinSalary(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalariesSumByDepartment(@PathVariable Integer id) {
        return departmentService.getSalariesSumByDepartment(id);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> getEmployeesByGroupDepartment() {
        return departmentService.allEmployeesDepartments();
    }
}
