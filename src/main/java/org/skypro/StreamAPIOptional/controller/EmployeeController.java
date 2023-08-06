package org.skypro.StreamAPIOptional.controller;

import org.skypro.StreamAPIOptional.entity.Employee;
import org.skypro.StreamAPIOptional.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handlerException(Exception e) {
        return "Code: " + e.getMessage();
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAll() {
        return employeeService.getAll().toString();//выводим всех сотрудников
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String lastName,
                        @RequestParam String firstName,
                        @RequestParam Integer department,
                        @RequestParam double salary) {
        return employeeService.add(lastName, firstName, salary, department);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String lastName,
                       @RequestParam String firstName,
                       @RequestParam double salary,
                       @RequestParam Integer department
                       ) {
        return employeeService.find(lastName, firstName, salary, department);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String lastName,
                         @RequestParam String firstName,
                         @RequestParam Integer department,
                         @RequestParam double salary) {
        return employeeService.remove(lastName, firstName, salary, department);
    }
}
