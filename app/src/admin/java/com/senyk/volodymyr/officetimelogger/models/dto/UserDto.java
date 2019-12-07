package com.senyk.volodymyr.officetimelogger.models.dto;

public class UserDto {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public UserDto(String firstName, String lastName, String middleName) {
        this(-1, firstName, lastName, middleName);
    }

    public UserDto(int id, String firstName, String lastName, String middleName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }
}
