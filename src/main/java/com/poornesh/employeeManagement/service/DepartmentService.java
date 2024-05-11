package com.poornesh.employeeManagement.service;

import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    DepartmentRepo dr;

    public DepartmentRepo getDr() {
        return dr;
    }

    @Autowired
    public void setDr(DepartmentRepo dr) {
        this.dr = dr;
    }


    public Department addDepartment(Department dp) {
        System.out.println("add called "+dp.getName());
        dr.save(dp);
        return dp;
    }

    public void findAll(){
        List<Department> list= dr.findAll();
        for(Department d:list){
            System.out.println(d.getName());
        }
    }

    public void showTables() {
        dr.showTables();
    }
}
