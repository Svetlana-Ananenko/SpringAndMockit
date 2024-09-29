    package com.homework.Spring_Mockito.controllers;

    import com.homework.Spring_Mockito.Employee;
    import com.homework.Spring_Mockito.exception.EmployeeAlreadyAddedException;
    import com.homework.Spring_Mockito.exception.EmployeeNotFoundException;
    import com.homework.Spring_Mockito.exception.EmployeeStorageIsFullException;
    import com.homework.Spring_Mockito.service.EmployeeServiceImpl;
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
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            } catch (EmployeeAlreadyAddedException e) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
            }
        }
        @GetMapping("/remove")
        public ResponseEntity<Employee> remove(@RequestParam("firstName") String firstName,
                                               @RequestParam("lastName") String lastName,
                                               @RequestParam("salary") Integer salary,
                                               @RequestParam("department") Integer department) {
            try {
                Employee removedEmployee = employeeServiceImpl.removeEmployee(firstName, lastName, salary, department);
                return ResponseEntity.ok(removedEmployee);
            } catch (EmployeeNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }

        @GetMapping(path = "/find")

        public ResponseEntity<Employee> find(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName,
                                             @RequestParam("salary") Integer salary,
                                             @RequestParam("department") Integer department) {
            try {
                Employee employee = employeeServiceImpl.findEmployee(firstName, lastName, salary, department);
                return ResponseEntity.ok(employee);
            } catch (EmployeeNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }


        @GetMapping(path = "/findAll")
        public ResponseEntity<Collection<Employee>> findAll() {
            Collection<Employee> employees = employeeServiceImpl.findAll();
            return ResponseEntity.ok(employees);
        }
    }

