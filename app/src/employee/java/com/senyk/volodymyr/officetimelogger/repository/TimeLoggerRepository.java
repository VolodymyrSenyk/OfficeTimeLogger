package com.senyk.volodymyr.officetimelogger.repository;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TimeLoggerRepository {
    Completable logIn(CredentialsDto creds);

    Completable logTime(TimeLogDto log);

    Single<UserDto> getUserData();

    Single<List<TimeLogDto>> deleteLog(int logId);

    Single<List<TimeLogDto>> getTimeLogs();

    Single<List<TimeLogDto>> getTimeLogs(long start, long end);

    Completable changePassword(Pair<String, String> passwords);
}
