package com.homework.Spring_Mockito;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeServiceInterface {


    Employee addEmployee(String firstName, String lastName, Integer salary, Integer department);

    Employee removeEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName, Integer salary, Integer department);

    Employee findEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName, Integer salary, Integer department);

    Collection<Employee> findAll();
}