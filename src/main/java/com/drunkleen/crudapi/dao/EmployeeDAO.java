package com.drunkleen.crudapi.dao;

import com.drunkleen.crudapi.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int employeeId);
    Employee save(Employee employee);
    void deleteById(int employeeId);

}
