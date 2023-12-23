package com.drunkleen.crudapi.dao;

import com.drunkleen.crudapi.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
