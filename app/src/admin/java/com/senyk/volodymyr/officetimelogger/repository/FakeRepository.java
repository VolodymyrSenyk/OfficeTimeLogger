package com.senyk.volodymyr.officetimelogger.repository;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements TimeLoggerRepository {
    private static FakeRepository repository;
    private static int count = 1000;
    private List<UserDto> users = new ArrayList<>();

    private FakeRepository() {
    }

    public static FakeRepository getFakeRepository() {
        if (repository == null) repository = new FakeRepository();
        return repository;
    }

    @Override
    public int addUser(UserDto user) {
        count++;
        users.add(new UserDto(
                count,
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName()
        ));
        return count;
    }

    @Override
    public boolean resetPassword(int userId) {
        return true;
    }

    @Override
    public List<UserDto> getUsers() {
        return users;
    }

    @Override
    public List<Pair<UserDto, TimeLogDto>> getLogByDay(long day) {
        List<Pair<UserDto, TimeLogDto>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(
                new UserDto(1243, "Ivan", "Ivanov", "Ivanovich"),
                new TimeLogDto(1, 1213L, 1254L, 12.23)
        ));
        pairs.add(new Pair<>(
                new UserDto(1343, "Petrov", "Petr", "Petrovich"),
                new TimeLogDto(2, 15213L, 15254L, 8.23)
        ));
        return pairs;
    }

    @Override
    public List<TimeLogDto> getLogByMonth(int mountNumber) {
        List<TimeLogDto> logs = new ArrayList<>();
        logs.add(new TimeLogDto(2, 15213L, 15254L, 8.23));
        logs.add(new TimeLogDto(1, 1213L, 1254L, 12.23));
        return logs;
    }
}
