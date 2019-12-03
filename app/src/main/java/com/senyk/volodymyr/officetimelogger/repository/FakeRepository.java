package com.senyk.volodymyr.officetimelogger.repository;

import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements TimeLoggerRepository {
    private int userId;
    private List<TimeLogDto> timeLogs = new ArrayList<>();

    private static FakeRepository repository;

    private FakeRepository() {
    }

    public static FakeRepository getFakeRepository() {
        return repository;
    }

    public int logIn(CredentialsDto creds) {
        this.userId = 123;
        return 123;
    }

    public boolean logTime(TimeLogDto log) {
        timeLogs.add(log);
        return log.getUserId() == this.userId;
    }

    public List<TimeLogDto> getTimeLogs() {
        List<TimeLogDto> filteredList = new ArrayList<>();
        for (TimeLogDto log : timeLogs) {
            if (this.userId == log.getUserId()) {
                filteredList.add(log);
            }
        }
        return filteredList;
    }

    public List<TimeLogDto> getTimeLogs(long start, long end) {
        List<TimeLogDto> filteredList = new ArrayList<>();
        for (TimeLogDto log : timeLogs) {
            if (this.userId == log.getUserId() && log.getArrivalTime() > start && log.getArrivalTime() < end) {
                filteredList.add(log);
            }
        }
        return filteredList;
    }
}
