package com.homework.Spring_Mockito;

import java.util.Objects;

import static org.springframework.util.StringUtils.capitalize;

public class Employee {
    private String firstName;
    private String lastName;
    private int salary;
    private int department;

    public Employee(String name, String surname, int salary, int department) {
        this.firstName = name;
        this.lastName = surname;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        String name = firstName.trim().replaceAll("[^a-zA-Zа-яА-Я ]", "");
        return capitalize(name);
    }

    public String getSurname() {
        String surname = lastName.trim().replaceAll("[^a-zA-Zа-яА-Я ]", "");
        return capitalize(lastName);
    }

    public String getFullName() {
        return getSurname() + " " + getName();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной.");
        }
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public int setDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Недопустимый номер отдела. Должно быть от 1 до 5.");
        }
        return department;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return getFullName();
    }
}