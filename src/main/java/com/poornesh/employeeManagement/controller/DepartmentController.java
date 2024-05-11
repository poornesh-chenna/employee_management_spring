package com.poornesh.employeeManagement.controller;

import com.poornesh.employeeManagement.dtos.DepartmentDTO;
import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    DepartmentService ds;



    public DepartmentService getDs() {
        return ds;
    }

    @Autowired
    public void setDs(DepartmentService ds) {
        this.ds = ds;
    }


    @PostMapping("/add")
    public ResponseEntity<Department> add(@Valid @RequestBody DepartmentDTO departmentDTO)
    {
        Department createdDepartment =  ds.addDepartment(departmentDTO.toDepartment());

        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }
}
