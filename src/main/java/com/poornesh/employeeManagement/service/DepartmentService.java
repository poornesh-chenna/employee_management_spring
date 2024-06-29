package com.poornesh.employeeManagement.service;

import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.model.Project;
import com.poornesh.employeeManagement.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo dr;


    public Department add(Department d) {
        System.out.println("add call" +"ed "+d.getName());
        dr.save(d);
        return d;
    }

    public int update(Department d){
        return dr.update(d);

    }

    public int delete(int id){
        return dr.delete(id);
    }


    public List<Department> findAll(){
        List<Department> list= dr.findAll();
        for(Department d:list){
            System.out.println(d.getName());
        }
        return list;
    }


    public Department find(int id) {
        return dr.find(id);
    }

    public List<Project> findProjectsUnderDep(int id){
       return dr.findProjects(id);
    }
}
