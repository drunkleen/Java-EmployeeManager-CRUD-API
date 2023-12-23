package com.drunkleen.crudapi.dao;

import com.drunkleen.crudapi.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        // Create a query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        // execute and return the results
        return query.getResultList();
    }

    @Override
    public Employee findById(int employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int employeeId) {
        Employee dbEmployee = entityManager.find(Employee.class, employeeId);
        entityManager.remove(dbEmployee);
    }
}
