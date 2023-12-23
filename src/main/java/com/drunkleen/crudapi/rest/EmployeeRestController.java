package com.drunkleen.crudapi.rest;

import com.drunkleen.crudapi.dao.EmployeeDAO;
import com.drunkleen.crudapi.entities.Employee;
import com.drunkleen.crudapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee findByEmployeeId(@PathVariable int employeeId) {
        Employee dbEmployee = employeeService.findById(employeeId);
        if (dbEmployee == null) {
            throw new RuntimeException("Employee id does not exist: " + employeeId);
        }
        return dbEmployee;
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int employeeId) {
        Employee dbEmployee = employeeService.findById(employeeId);
        if (dbEmployee == null) {
            throw new RuntimeException("Employee id does not exist: " + employeeId);
        }
        if (employee.getFirstName() != null) {
            dbEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            dbEmployee.setLastName(employee.getLastName());
        }
        if (employee.getEmail() != null) {
            dbEmployee.setEmail(employee.getEmail());
        }

        return employeeService.save(dbEmployee);
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee dbEmployee = employeeService.findById(employeeId);
//        if (dbEmployee == null) {
//            throw new RuntimeException("Employee id does not exist: " + employeeId);
//        }

        employeeService.deleteById(employeeId);

        return "Employee with ID " + employeeId + " deleted.";
    }


}
