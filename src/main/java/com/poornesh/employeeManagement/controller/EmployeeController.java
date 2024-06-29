package com.poornesh.employeeManagement.controller;

import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.model.Employee;
import com.poornesh.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService es;

    @GetMapping("/get")
    public String get(){
        return "server up ";
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> add(@RequestBody Employee e){
        System.out.println("in post "+e);
        Employee createdEmployee = es.add(e);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Employee e,@PathVariable int id)
    {
        System.out.println(e);
        int rows = es.update(id,e);
        if(rows == 0)
            return new ResponseEntity<>("Id "+id+" does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Successfully Updated",HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        int rows = es.delete(id);
        if(rows == 0)
            return new ResponseEntity<>("Id "+id+" does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Successfully deleted",HttpStatus.ACCEPTED);

    }

    @GetMapping("/")
    public List<Employee> findAll(){
        return es.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id){
        Employee e = es.find(id);
        if(e == null)
            return new ResponseEntity<>("Employee with ID " + id + " does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(e,HttpStatus.ACCEPTED);

    }


}
