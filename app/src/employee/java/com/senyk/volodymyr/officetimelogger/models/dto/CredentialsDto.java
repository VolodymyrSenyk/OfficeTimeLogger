package com.senyk.volodymyr.officetimelogger.models.dto;

public class CredentialsDto {
    private final int userNumber;
    private final int password;

    public CredentialsDto(int userNumber, int password) {
        this.userNumber = userNumber;
        this.password = password;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public int getPassword() {
        return password;
    }
}
