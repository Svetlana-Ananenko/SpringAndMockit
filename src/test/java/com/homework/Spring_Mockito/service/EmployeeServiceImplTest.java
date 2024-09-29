package com.homework.Spring_Mockito.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.sky.homework25.Employee;
import pro.sky.homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.homework25.exceptions.EmployeeNotFoundException;
import pro.sky.homework25.exceptions.EmployeeStorageIsFullException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUpCollectionPeople() {
        employeeService = new EmployeeServiceImpl();
        String firstName1 = "Иван";
        String lastName1 = "Иванов";
        int salary1 = 50000;
        int department1 = 1;
        employee1 = employeeService.addEmployee(firstName1, lastName1, salary1, department1);

        String firstName2 = "Петр";
        String lastName2 = "Петров";
        int salary2 = 60000;
        int department2 = 2;
        employee2 = employeeService.addEmployee(firstName2, lastName2, salary2, department2);
    }

    @Test
    @DisplayName("Успешное добавление нового сотрудника")
    void shouldAddNewEmployee() {
        Assertions.assertNotNull(employee1);
        Assertions.assertEquals("Иванов Иван", employee1.getFullName());
        Assertions.assertEquals(50000, employee1.getSalary());
        Assertions.assertEquals(1, employee1.getDepartment());
    }

    @Test
    @DisplayName("Не удается добавить сотрудника, достигнут лимит с существующими")
    void shouldThrowExceptionOnMaxEmployeesReachedWithExisting() {

        int maxEmployees = 20;
        for (int i = 0; i < maxEmployees - 2; i++) {
            employeeService.addEmployee("FirstName " + i, "LastName " + i, 50000, 1);
        }

        Assertions.assertThrows(EmployeeStorageIsFullException.class, () ->
                employeeService.addEmployee("NewFirstName", "NewLastName", 50000, 2)
        );
    }

    @Test
    @DisplayName("Повторное добавление существующего сотрудника с существующими")
    void shouldThrowExceptionOnDuplicateEmployeeWithExisting() {
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 50000;
        int department = 1;

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee(firstName, lastName, salary, department)
        );
    }

    @Test
    @DisplayName("Удаление существующего сотрудника")
    void shouldRemoveExistingEmployee() {

        Employee actualEmployee = employeeService.removeEmployee(employee1.getName(), employee1.getSurname(), employee1.getSalary(), employee1.getDepartment());

        Assertions.assertNotNull(actualEmployee);
        Assertions.assertEquals(employee1.getFullName(), actualEmployee.getFullName());
        Assertions.assertEquals(employee1.getSalary(), actualEmployee.getSalary());
        Assertions.assertEquals(employee1.getDepartment(), actualEmployee.getDepartment());
    }

    @Test
    @DisplayName("Попытка удаления несуществующего сотрудника")
    void shouldThrowExceptionWhenTryingToRemoveNonexistentEmployee() {

        String firstName = "Василий";
        String lastName = "Васильев";
        int salary = 70000;
        int department = 3;

        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.removeEmployee(firstName, lastName, salary, department),
                "Ожидалось, что будет выброшено исключение EmployeeNotFoundException, но этого не произошло"
        );
    }

    @Test
    @DisplayName("Поиск существующего сотрудника")
    void shouldFindExistingEmployee() {
        Employee actualEmployee = employeeService.findEmployee(employee1.getName(), employee1.getSurname(), employee1.getSalary(), employee1.getDepartment());

        Assertions.assertNotNull(actualEmployee);
        Assertions.assertEquals(employee1.getFullName(), actualEmployee.getFullName());
        Assertions.assertEquals(employee1.getSalary(), actualEmployee.getSalary());
        Assertions.assertEquals(employee1.getDepartment(), actualEmployee.getDepartment());
    }

    @Test
    @DisplayName("Попытка поиска несуществующего сотрудника")
    void shouldThrowExceptionWhenTryingToFindNonexistentEmployee() {

        String firstName = "Василий";
        String lastName = "Васильев";
        int salary = 70000;
        int department = 3;

        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.findEmployee(firstName, lastName, salary, department),
                "Ожидалось, что будет выброшено исключение EmployeeNotFoundException, но этого не произошло"
        );
    }

    @Test
    @DisplayName("Получение всех сотрудников")
    void shouldReturnAllEmployees() {

        Collection<Employee> allEmployees = employeeService.findAll();

        Assertions.assertNotNull(allEmployees);
        Assertions.assertEquals(2, allEmployees.size());
        Assertions.assertTrue(allEmployees.contains(employee1));
        Assertions.assertTrue(allEmployees.contains(employee2));
    }

    @Test
    @DisplayName("Получение списка сотрудников по отделам")
    void shouldGroupEmployeesByDepartment() {

        Map<Integer, List<Employee>> employeesByDepartment = employeeService.allEmployeesDepartments();

        Assertions.assertNotNull(employeesByDepartment);
        Assertions.assertEquals(2, employeesByDepartment.size());

        List<Employee> department1Employees = employeesByDepartment.get(1);
        Assertions.assertNotNull(department1Employees);
        Assertions.assertEquals(1, department1Employees.size());
        Assertions.assertTrue(department1Employees.contains(employee1));

        List<Employee> department2Employees = employeesByDepartment.get(2);
        Assertions.assertNotNull(department2Employees);
        Assertions.assertEquals(1, department2Employees.size());
        Assertions.assertTrue(department2Employees.contains(employee2));
    }

    @Test
    @DisplayName("Получение общей суммы зарплат всех сотрудников")
    void shouldCalculateTotalSalary() {

        int totalSalary = employeeService.getTotalSalary();

        Assertions.assertEquals(110000, totalSalary);
    }
}
