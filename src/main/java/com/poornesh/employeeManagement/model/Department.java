package com.poornesh.employeeManagement.model;

import org.springframework.stereotype.Component;

@Component
public class Department {
    private int depId;
    private String name;

    public int getDepId() {
        return depId;
    }

    public Department setDepId(int depId) {
        this.depId = depId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }
}
