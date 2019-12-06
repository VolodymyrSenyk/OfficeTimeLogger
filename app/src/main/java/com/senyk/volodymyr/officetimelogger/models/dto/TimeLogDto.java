package com.senyk.volodymyr.officetimelogger.models.dto;

public class TimeLogDto {
    private final int id;
    private final long arrivalTime;
    private final long leavingTime;
    private final double totalTime;

    public TimeLogDto(long arrivalTime, long leavingTime) {
        this(-1, arrivalTime, leavingTime, 0.0);
    }

    public TimeLogDto(int id, long arrivalTime, long leavingTime, double totalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.totalTime = totalTime;
    }

    public int getId() {
        return this.id;
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
