package org.skypro.StreamAPIOptional.entity;

import java.util.Objects;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final int department;
    private final double salary;


    public Employee(String lastName, String firstName, double salary, int department) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return
                "[" + lastName + ",  " + firstName + ",  " + salary + ",  " + department + "]  ";
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