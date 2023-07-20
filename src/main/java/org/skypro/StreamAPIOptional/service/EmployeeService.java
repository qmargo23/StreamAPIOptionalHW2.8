package org.skypro.StreamAPIOptional.service;

import org.skypro.StreamAPIOptional.entity.Employee;
import org.skypro.StreamAPIOptional.exception.EmployeeAlreadyAddedException;
import org.skypro.StreamAPIOptional.exception.EmployeeNotFoundException;
import org.skypro.StreamAPIOptional.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    Map<String, Employee> employeeMap = new HashMap<>(Map.of(
            "ВавиловОлег",
            new Employee("Вавилов", "Олег", "1", "80_000.500")));

    private final static int MAX_SIZE = 3;//максимальное кол сотрудников

    public String add(String lastName, String firstName, String department, String salary) {
        if (employeeMap.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("переполнение массива сотрудников");
        }
        String key = lastName + firstName;
        Employee newEmployee = new Employee(lastName, firstName, department, salary);
        employeeMap.put(key, newEmployee);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("такой сотрудник уже есть");
        }
        employees.add(newEmployee);
        return "Добавлен___" + newEmployee;
    }

    public String find(String lastName, String firstName) {
        String key = lastName + firstName;
        if (employeeMap.containsKey(key)) {
            return "Найден___" + employeeMap.get(key);
        }
        throw new EmployeeNotFoundException("сотрудник не найден");
    }

    public String remove(String lastName, String firstName) {
        Employee forRemove = new Employee(lastName, firstName);
        String key = lastName + firstName;
        String result = find(lastName , firstName );
        employeeMap.remove(key);
        employees.remove(forRemove);
        return result + "___Удален!";
    }

    public String getAll() {
        return String.valueOf(employeeMap);
    }
}
