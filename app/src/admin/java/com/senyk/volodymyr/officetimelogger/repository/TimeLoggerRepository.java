package com.senyk.volodymyr.officetimelogger.repository;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;

import java.util.List;

public interface TimeLoggerRepository {
    int addUser(UserDto user);

    boolean resetPassword(int userId);

    List<UserDto> getUsers();

    List<Pair<UserDto, TimeLogDto>> getLogByDay(long day);

    List<TimeLogDto> getLogByMonth(int mountNumber);
}
