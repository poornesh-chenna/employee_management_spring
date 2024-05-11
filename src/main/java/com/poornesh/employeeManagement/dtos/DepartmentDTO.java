package com.poornesh.employeeManagement.dtos;

import com.poornesh.employeeManagement.model.Department;

import jakarta.validation.constraints.*;
import lombok.Data;


//@Data
public class DepartmentDTO {

    @NotBlank(message = "Department id is required")
    private int depId;

    @NotNull(message = "Department name is required")
    private String name;

    @AssertTrue
    boolean isNameNotBlank() {
        System.out.println("debug poiny");
        return name != null;
    }

    public Department toDepartment(){
        return new Department()
                .setDepId(depId)
                .setName(name);
    }
}
