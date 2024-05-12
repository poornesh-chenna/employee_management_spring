package com.poornesh.employeeManagement.service;

import com.poornesh.employeeManagement.dtos.ErrorResponse;
import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.model.Project;
import com.poornesh.employeeManagement.repo.ProjectRepo;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepo pr;

    public Project add(Project p){
        System.out.println("in project service "+p.getName());
        pr.save(p);
        return p;
    }

    public Either<ErrorResponse, Integer> update(Project p) {
        return pr.update(p);
    }

    public int delete(int id){
        return pr.delete(id);
    }

    public List<Project> findAll() {
        List<Project> list= pr.findAll();
        for(Project p:list){
            System.out.println(p.getName());
        }
        return list;
    }

    public Project find(int id) {
        return pr.find(id);
    }
}
