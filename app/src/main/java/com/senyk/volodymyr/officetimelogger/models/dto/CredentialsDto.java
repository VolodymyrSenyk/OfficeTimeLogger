package com.senyk.volodymyr.officetimelogger.models.dto;

public class CredentialsDto {
    private final EmployeeDto employee;
    private final String password;

    public EmployeeDto getEmployee() {
        return this.employee;
    }

    public String getPassword() {
        return this.password;
    }

    public CredentialsDto(EmployeeDto employee, String password) {
        this.employee = employee;
        this.password = password;
    }
}
