package com.senyk.volodymyr.officetimelogger.models.dto;

public class TimeLogDto {
    private final long arrivalTime;
    private final long leavingTime;
    private final double totalTime;

    public TimeLogDto(long arrivalTime, long leavingTime) {
        this(arrivalTime, leavingTime, 0.0);
    }

    public TimeLogDto(long arrivalTime, long leavingTime, double totalTime) {
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.totalTime = totalTime;
    }

    public long getArrivalTime() {
        return this.arrivalTime;
    }

    public long getLeavingTime() {
        return this.leavingTime;
    }

    public double getTotalTime() {
        return this.totalTime;
    }
}
