package com.senyk.volodymyr.officetimelogger.repository;

import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements TimeLoggerRepository {
    private static FakeRepository repository;
    private List<TimeLogDto> timeLogs = new ArrayList<>();

    private FakeRepository() {
    }

    public static FakeRepository getFakeRepository() {
        if (repository == null) repository = new FakeRepository();
        return repository;
    }

    public int logIn(CredentialsDto creds) {
        return 123;
    }

    public boolean logTime(TimeLogDto log) {
        timeLogs.add(log);
        return true;
    }

    public List<TimeLogDto> getTimeLogs() {
        return timeLogs;
    }

    public List<TimeLogDto> getTimeLogs(long start, long end) {
        List<TimeLogDto> filteredList = new ArrayList<>();
        for (TimeLogDto log : timeLogs) {
            if (log.getArrivalTime() > start && log.getArrivalTime() < end) {
                filteredList.add(log);
            }
        }
        return filteredList;
    }
}
