package com.senyk.volodymyr.officetimelogger.models.dto;

public class EmployeeDto {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public EmployeeDto(String firstName, String lastName, String middleName) {
        this(0, firstName, lastName, middleName);
    }

    public EmployeeDto(int id, String firstName, String lastName, String middleName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
