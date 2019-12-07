package com.senyk.volodymyr.officetimelogger.mappers.dtoui;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;

import java.util.ArrayList;
import java.util.List;

public class PairsMapper {
    private final TimeLogsMapper logsMapper;
    private final UsersMapper usersMapper;

    public PairsMapper(TimeLogsMapper logsMapper, UsersMapper usersMapper) {
        this.logsMapper = logsMapper;
        this.usersMapper = usersMapper;
    }

    public List<Pair<UserUi, TimeLogUi>> convertToUi(List<Pair<UserDto, TimeLogDto>> dtoList) {
        List<Pair<UserUi, TimeLogUi>> uiList = new ArrayList<>(dtoList.size());
        for (Pair<UserDto, TimeLogDto> dto : dtoList) {
            uiList.add(new Pair<>(
                    usersMapper.convertToUi(dto.first),
                    logsMapper.convertToUi(dto.second)
            ));
        }
        return uiList;
    }
}
