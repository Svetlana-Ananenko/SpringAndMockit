    package com.homework.Spring_Mockito.controllers;

    import com.homework.Spring_Mockito.Employee;
    import com.homework.Spring_Mockito.EmployeeServiceNew;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.Collection;
    import java.util.List;
    import java.util.Map;

    @RestController
    @RequestMapping("/departments")
    public class DepartmentsController {
        private final EmployeeServiceNew employeeServiceNew;

        @Autowired
        public DepartmentsController(EmployeeServiceNew employeeServiceNew) {
            this.employeeServiceNew = employeeServiceNew;
        }

        @GetMapping("/max-salary")
        public Employee maxSalaryDepartment(@RequestParam int department) {
            return employeeServiceNew.getEmployeeWithMaxSalary(department);
        }

        @GetMapping("/min-salary")
        public Employee minSalaryDepartment(@RequestParam int department) {
            return employeeServiceNew.getEmployeeWithMinSalary(department);
        }

        @GetMapping(value = "/all", params = {"department"})
        public Collection<Employee> allDepartment(@RequestParam int department) {
            return employeeServiceNew.employeesDepartment(department);
        }

        @GetMapping("/all")
        public Map<Integer, List<Employee>> all() {
            return employeeServiceNew.allEmployeesDepartments();
        }
    }