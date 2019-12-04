package com.senyk.volodymyr.officetimelogger.models.dto;

public class CredentialsDto {
    private final String userNumber;
    private final String password;

    public String getUserNumber() {
        return userNumber;
    }

    public String getPassword() {
        return password;
    }

    public CredentialsDto(String userNumber, String password) {
        this.userNumber = userNumber;
        this.password = password;
    }
}
