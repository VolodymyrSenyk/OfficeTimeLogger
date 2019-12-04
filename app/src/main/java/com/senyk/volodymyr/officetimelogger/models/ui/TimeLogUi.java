package com.senyk.volodymyr.officetimelogger.models.ui;

public class TimeLogUi {
    private final String day;
    private final String arrivalTime;
    private final String leavingTime;
    private final String totalTime;

    public TimeLogUi(String day, String arrivalTime, String leavingTime, String totalTime) {
        this.day = day;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.totalTime = totalTime;
    }

    public String getDay() {
        return this.day;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public String getLeavingTime() {
        return this.leavingTime;
    }

    public String getTotalTime() {
        return this.totalTime;
    }
}
