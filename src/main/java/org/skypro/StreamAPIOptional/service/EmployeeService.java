package org.skypro.StreamAPIOptional.service;

import org.apache.commons.lang3.StringUtils;
import org.skypro.StreamAPIOptional.entity.Employee;
import org.skypro.StreamAPIOptional.exception.BadRequestException;
import org.skypro.StreamAPIOptional.exception.EmployeeAlreadyAddedException;
import org.skypro.StreamAPIOptional.exception.EmployeeNotFoundException;
import org.skypro.StreamAPIOptional.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    private final static int MAX_SIZE = 5;//максимальное кол сотрудников

    public Employee add(String lastName, String firstName, double salary, int department) {

        validateFullName(firstName, lastName);

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
    private void validateFullName(String firstName, String lastName) {
        validateCapitalize(firstName, lastName);
        validateIsAlpha(firstName, lastName);
    }
    private void validateCapitalize(String firstName, String lastName) {
        String capitalizeFirstName = StringUtils.capitalize(firstName);

        if (!firstName.equals(capitalizeFirstName)) {
            throw new BadRequestException("Имя начинается не с заглавной буквы");
        }

        String capitalizeLastName = StringUtils.capitalize(lastName);

        if (!lastName.equals(capitalizeLastName)) {
            throw new BadRequestException("Фамилия начинается не с заглавной буквы");
        }
    }

    private void validateIsAlpha(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new BadRequestException("Имя должно содержать только буквы");
        }

        if (!StringUtils.isAlpha(lastName)) {
            throw new BadRequestException("Фамилия должна содержать только буквы");
        }
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
