package com.senyk.volodymyr.officetimelogger.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PairResponse {
    @SerializedName("employee")
    @Expose
    private Employee employee;
    @SerializedName("log")
    @Expose
    private TimeLog log;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TimeLog getLog() {
        return log;
    }

    public void setLog(TimeLog log) {
        this.log = log;
    }
}
