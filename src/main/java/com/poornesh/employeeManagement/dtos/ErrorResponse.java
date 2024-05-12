package com.poornesh.employeeManagement.dtos;

public class ErrorResponse {

    private String reason;

    public ErrorResponse(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }
}
