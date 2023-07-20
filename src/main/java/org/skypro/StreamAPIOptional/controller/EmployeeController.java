package org.skypro.StreamAPIOptional.controller;

import org.skypro.StreamAPIOptional.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

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

    @GetMapping("/add")
    public String add(@RequestParam String lastName,
                      @RequestParam String firstName,
                      @RequestParam String department,
                      @RequestParam String salary) {
        return employeeService.add(lastName, firstName, department, salary);
    }

    @GetMapping("/get")
    public String getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/find")
    public String find(@RequestParam String lastName,
                       @RequestParam String firstName,
                       @RequestParam String department,
                       @RequestParam String salary) {
        return employeeService.find(lastName, firstName);
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String lastName,
                         @RequestParam String firstName) {
        return employeeService.remove(lastName, firstName);
    }
}
