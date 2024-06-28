package com.poornesh.employeeManagement.service;

import com.poornesh.employeeManagement.model.Employee;
import com.poornesh.employeeManagement.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeService {
    @Autowired
    EmployeeRepo er;

}
