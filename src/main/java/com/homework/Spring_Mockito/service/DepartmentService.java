package com.homework.Spring_Mockito.service;

import com.homework.Spring_Mockito.Employee;


import java.util.List;
import java.util.Map;



public interface DepartmentService {

    List<Employee> employeesDepartment(Integer department);

    int getEmployeeWithMaxSalary(Integer department);

    int getEmployeeWithMinSalary(Integer department);

    Map<Integer, List<Employee>> allEmployeesDepartments();

    int getSalariesSumByDepartment(Integer departmentId);
}