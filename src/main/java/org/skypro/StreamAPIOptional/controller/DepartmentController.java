package org.skypro.StreamAPIOptional.controller;

import org.skypro.StreamAPIOptional.entity.Employee;
import org.skypro.StreamAPIOptional.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public String getEmployeeWithMaxSalary(@RequestParam Integer department) {
        return "Найден сотрудник с максимльной зарплатой в отделе №" + department + " " + departmentService.getEmployeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam Integer department) {
        return departmentService.getEmployeeWithMinSalary(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesByDepartment(@RequestParam(required = false) Integer department) {
        return departmentService.getEmployeesByDepartment(department);
    }
}