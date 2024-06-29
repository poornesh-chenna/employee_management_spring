package com.poornesh.employeeManagement.controller;

import com.poornesh.employeeManagement.dtos.DepartmentDTO;
import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    DepartmentService ds;


    @PostMapping("/add")
    public ResponseEntity<Department> add(@Valid @RequestBody DepartmentDTO departmentDTO)
    {
//        ResponseEntity is a flexible class in Spring Boot used to handle HTTP responses. It represents the whole HTTP response, including the status code, headers, and body.


            System.out.println(departmentDTO);
        Department createdDepartment =  ds.add(departmentDTO.toDepartment());

        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Department d)
    {
        System.out.println(d);
        int rows = ds.update(d);
        if(rows == 0)
            return new ResponseEntity<>("Id "+d.getDepId()+" does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Successfully Updated",HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        int rows = ds.delete(id);
        if(rows == 0)
            return new ResponseEntity<>("Id "+id+" does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Successfully deleted",HttpStatus.ACCEPTED);

    }

    @GetMapping("/")
    public List<Department> findAll(){
        return ds.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id){
        Department d = ds.find(id);
        if(d == null)
            return new ResponseEntity<>("Department with ID " + id + " does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(d,HttpStatus.ACCEPTED);

    }

    @GetMapping("/projectsUnder/{id}")
    public ResponseEntity<?> findProjectsUnderDep(@PathVariable int id){
        Department d = ds.find(id);
        if(d == null)
            return new ResponseEntity<>("Department with ID " + id + " does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(ds.findProjectsUnderDep(id),HttpStatus.ACCEPTED);

    }
}
