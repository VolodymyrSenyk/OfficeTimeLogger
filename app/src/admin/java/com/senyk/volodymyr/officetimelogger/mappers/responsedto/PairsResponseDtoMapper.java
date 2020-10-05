package com.senyk.volodymyr.officetimelogger.mappers.responsedto;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.response.PairResponse;

import java.util.ArrayList;
import java.util.List;

public class PairsResponseDtoMapper {
    private final UsersResponseDtoMapper userMapper;
    private final TimeLogsResponseDtoMapper logMapper;

    public PairsResponseDtoMapper(UsersResponseDtoMapper userMapper, TimeLogsResponseDtoMapper logMapper) {
        this.userMapper = userMapper;
        this.logMapper = logMapper;
    }

    public List<Pair<UserDto, TimeLogDto>> convertToDto(List<PairResponse> responseList) {
        List<Pair<UserDto, TimeLogDto>> dtoList = new ArrayList<>(responseList.size());
        for (PairResponse response : responseList) {
            dtoList.add(new Pair<>(
                    userMapper.convertToDto(response.getEmployee()),
                    logMapper.convertToDto(response.getLog())
            ));
        }
        return dtoList;
    }
}
