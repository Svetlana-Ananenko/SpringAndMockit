package com.homework.Spring_Mockito;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeServiceNewInterface {

    Employee getEmployeeWithMaxSalary(int department);

    Employee getEmployeeWithMinSalary(int department);

    Collection<Employee> employeesDepartment(int department);

    Map<Integer, List<Employee>> allEmployeesDepartments();
}