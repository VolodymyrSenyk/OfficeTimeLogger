package com.senyk.volodymyr.officetimelogger.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponse {
    @SerializedName("employee")
    @Expose
    private List<Employee> employee = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
