package com.senyk.volodymyr.officetimelogger.repository;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TimeLoggerRepository {
    Completable addUser(UserDto user);

    Completable resetPassword(int userId);

    Single<List<UserDto>> getUsers();

    Single<List<Pair<UserDto, TimeLogDto>>> getLogByDay(long start, long end);

    Single<List<TimeLogDto>> getLogByMonth(int userId, int mountNumber);
}
