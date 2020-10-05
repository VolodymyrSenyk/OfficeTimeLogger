package com.senyk.volodymyr.officetimelogger.models.ui;

public class TimeLogUi {
    private final int id;
    private final String day;
    private final String arrivalTime;
    private final String leavingTime;
    private final String totalTime;

    public TimeLogUi(int id, String day, String arrivalTime, String leavingTime, String totalTime) {
        this.id = id;
        this.day = day;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.totalTime = totalTime;
    }

    public int getId() {
        return this.id;
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
