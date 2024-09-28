   package com.homework.Spring_Mockito;

   import com.homework.Spring_Mockito.exception.EmployeeAlreadyAddedException;
   import com.homework.Spring_Mockito.exception.EmployeeNotFoundException;
   import com.homework.Spring_Mockito.exception.EmployeeStorageIsFullException;
   import jakarta.annotation.PostConstruct;
   import org.springframework.stereotype.Service;


   import java.util.*;

   @Service
   public class EmployeeServiceImpl implements EmployeeServiceInterface {
       private final int MAX_EMPLOYEES = 20;
       private final Map<String, Employee> employees = new HashMap<>();

       @PostConstruct
       private void listWithEmployees() {
           addEmployee("Костя", "Маласаев", 50_000, 1);
           addEmployee("Андрюха", "Шелков", 70_000, 2);
           addEmployee("Димон", "Вьюшкин", 40_000, 2);
           addEmployee("Серега", "Гореликов", 80_000, 3);
           addEmployee("АндрейКа", "Минин", 65_000, 4);
           addEmployee("Никита", "Мемасиков", 45_000, 1);
           addEmployee("Стас", "Выжил", 80_000, 5);
           addEmployee("Гена", "СпанчБобский", 75_000, 4);
           addEmployee("Турбо", "Джавович", 100_000, 5);
           addEmployee("Дюша", "Кофеинов", 75_000, 3);
       }


       @Override
       public Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) {
           if (employees.size() >= MAX_EMPLOYEES) {
               throw new EmployeeStorageIsFullException("Зарплаты на всех не хватит, макс. кол-во сотрудников - " + MAX_EMPLOYEES);
           }
           Employee employee = new Employee(firstName, lastName, salary.intValue(), department.intValue());
           if (employees.containsKey(employee.getFullName())) {
               throw new EmployeeAlreadyAddedException("Этот " + employee.getFullName() + " сотрудник уже есть");
           }
           employees.put(employee.getFullName(), employee);
           return employee;
       }

       @Override
       public Employee removeEmployee(String firstName, String lastName) {
           return null;
       }

       @Override
       public Employee removeEmployee(String firstName, String lastName, Integer salary, Integer department) {
           Employee employee = new Employee(firstName, lastName, salary, department);
           if (employees.containsKey(employee.getFullName())) {
               return employees.remove(employee.getFullName());
           }
           throw new EmployeeNotFoundException("Такого сотрудника " + employee.getFullName() + " нет в базе, увы");
       }

       @Override
       public Employee findEmployee(String firstName, String lastName) {
           return null;
       }

       @Override
       public Employee findEmployee(String firstName, String lastName, Integer salary, Integer department) {
           Employee employee = new Employee(firstName, lastName, salary, department);
           if (!employees.containsKey(employee.getFullName())) {
               throw new EmployeeNotFoundException("Такого сотрудника " + employee.getFullName() + " нет в базе, увы");
           }
           return employees.get(employee.getFullName());
       }

       @Override
       public Collection<Employee> findAll() {
           return Collections.unmodifiableCollection(employees.values());
       }
   }