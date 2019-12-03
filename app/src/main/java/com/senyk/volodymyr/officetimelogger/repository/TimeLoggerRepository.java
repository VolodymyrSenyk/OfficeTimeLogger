package com.senyk.volodymyr.officetimelogger.repository;

import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;

import java.util.List;

public interface TimeLoggerRepository {
    int logIn(CredentialsDto creds);

    boolean logTime(TimeLogDto log);

    List<TimeLogDto> getTimeLogs();

    List<TimeLogDto> getTimeLogs(long start, long end);
}
