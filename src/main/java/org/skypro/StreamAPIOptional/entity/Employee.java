package org.skypro.StreamAPIOptional.entity;

import java.util.Objects;

public class Employee {

    private final String firstName;
    private final String lastName;
    private String department;
    private String salary;


    public Employee(String lastName, String firstName, String department, String salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "\t&#128073;" +
                " lastName: '" + lastName + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", department: " + department +
                ", salary = " + salary +
                " \t&#127937;; ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}