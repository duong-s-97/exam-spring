package com.example.exam_spring.controller;

import com.example.exam_spring.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;


@Controller
public class EmployeeController {
    private final EntityManagerFactory entityManagerFactory;
    public  EmployeeController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    public Employee createEmployee(String name, BigDecimal wage) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Employee employee = new Employee(name, wage);
        entityManager.persist(employee);

        transaction.commit();
        entityManager.close();

        return employee;
    }

    public List<Employee> findAllEmployees() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        transaction.commit();
        entityManager.close();
        return employees;
    }



    }
