package com.poornesh.employeeManagement.service;

import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.model.Employee;
import com.poornesh.employeeManagement.model.Project;
import com.poornesh.employeeManagement.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo er;

    public Employee add(Employee e){
        System.out.println("in project service "+e.getName());
        er.save(e);
        return e;
    }


    public int update(int id,Employee e){
        return er.update(id,e);

    }

    public int delete(int id){
        return er.delete(id);
    }


    public List<Employee> findAll(){
        List<Employee> list= er.findAll();
        for(Employee e:list){
            System.out.println(e.getName());
        }
        return list;
    }


    public Employee find(int id) {
        return er.find(id);
    }

}
