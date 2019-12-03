package com.senyk.volodymyr.officetimelogger.models.dto;

public class TimeLogDto {
    private final int userId;
    private final long arrivalTime;
    private final long leavingTime;
    private final double totalTime;

    public int getUserId() {
        return this.userId;
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

    public TimeLogDto(int userId, long arrivalTime, long leavingTime) {
        this(userId, arrivalTime, leavingTime, 0.0);
    }

    public TimeLogDto(int userId, long arrivalTime, long leavingTime, double totalTime) {
        this.userId = userId;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.totalTime = totalTime;
    }
}
