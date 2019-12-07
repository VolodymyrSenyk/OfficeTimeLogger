package com.senyk.volodymyr.officetimelogger.models.ui;

public class UserUi {
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public UserUi(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
