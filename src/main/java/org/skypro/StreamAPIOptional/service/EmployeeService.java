package org.skypro.StreamAPIOptional.service;

import org.skypro.StreamAPIOptional.entity.Employee;
import org.skypro.StreamAPIOptional.exception.EmployeeAlreadyAddedException;
import org.skypro.StreamAPIOptional.exception.EmployeeNotFoundException;
import org.skypro.StreamAPIOptional.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee("Акатьев", "Андрей", 15000, 1));
        employees.add(new Employee("Архипов", "Андрей", 10000, 1));
        employees.add(new Employee("Астафьев", "Андрей", 1800.50, 1));

        employees.add(new Employee("Глухов", "Глеб", 122345.50, 2));
        employees.add(new Employee("Горохов", "Глеб", 1234.50, 2));

        employees.add(new Employee("Дрожкин", "Денис", 30003.50, 3));
        employees.add(new Employee("Дорофеев", "Денис", 333333.80, 3));
    }

    private final static int MAX_SIZE = 8;//максимальное кол сотрудников

    public Employee add(String lastName, String firstName, double salary, int department) {

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmployee = new Employee(firstName, lastName, salary, department);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        employees.add(newEmployee);
        return newEmployee;

    }

    public Employee find(String firstName, String lastName, double salary, int department) {
        Employee employeeForFound = new Employee(firstName, lastName, salary, department);
        for (Employee e : employees) {
            if (e.equals(employeeForFound)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }

    public Employee remove(String firstName, String lastName, double salary, int departmentId) {
        Employee employeeForRemove = new Employee(firstName, lastName, salary, departmentId);

        boolean removeResult = employees.remove(employeeForRemove);
        if (removeResult) {
            return employeeForRemove;
        } else {
            throw new EmployeeNotFoundException("Сотрудник  не был найден в базе");
        }
    }

    public List<Employee> getAll() {
        return employees;
    }
}
