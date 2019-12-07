package com.senyk.volodymyr.officetimelogger.repository;

import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TimeLoggerRepository {
    Completable addUser(UserDto user);

    Completable resetPassword(int userId);

    Single<List<UserDto>> getUsers();

    //  List<Pair<UserDto, TimeLogDto>> getLogByDay(long day);

    //  List<TimeLogDto> getLogByMonth(int mountNumber);
}
