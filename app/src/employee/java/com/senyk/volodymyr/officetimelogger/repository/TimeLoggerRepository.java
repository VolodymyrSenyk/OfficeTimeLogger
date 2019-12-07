package com.senyk.volodymyr.officetimelogger.repository;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;

import java.util.List;

public interface TimeLoggerRepository {
    int logIn(CredentialsDto creds);

    boolean logTime(TimeLogDto log);

    List<TimeLogDto> deleteLog(int logId);

    List<TimeLogDto> getTimeLogs();

    List<TimeLogDto> getTimeLogs(long start, long end);

    boolean changePassword(Pair<String, String> passwords);
}
