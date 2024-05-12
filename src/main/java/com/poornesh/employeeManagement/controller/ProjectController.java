package com.poornesh.employeeManagement.controller;

import com.poornesh.employeeManagement.dtos.ErrorResponse;
import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.model.Project;
import com.poornesh.employeeManagement.service.ProjectService;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    ProjectService ps;

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        Project createdProject = ps.add(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody Project p){
        Either<ErrorResponse, Integer> updateEither = ps.update(p);
        if (updateEither.isLeft()) {
            ErrorResponse errorResponse = updateEither.getLeft();
            return new ResponseEntity<>(errorResponse.getReason(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Successfully Updated",HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        int rows = ps.delete(id);
        if(rows == 0)
            return new ResponseEntity<>("Id "+id+" does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Successfully deleted",HttpStatus.ACCEPTED);

    }


    @GetMapping("/")
    public List<Project> findAll(){
        return ps.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id){
        Project p = ps.find(id);
        if(p == null)
            return new ResponseEntity<>("Project with ID " + id + " does not exist.",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(p,HttpStatus.ACCEPTED);

    }
}
