package com.senyk.volodymyr.officetimelogger.mappers.responsedto;

import android.util.Log;
import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.response.PairResponse;
import com.senyk.volodymyr.officetimelogger.models.response.PairsResponse;

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
        Log.e("TAG_1", responseList.size()+"");
        List<Pair<UserDto, TimeLogDto>> dtoList = new ArrayList<>(responseList.size());
        for (PairResponse response : responseList) {
            Log.e("TAG", "enter");
            dtoList.add(new Pair<>(
                    userMapper.convertToDto(response.getEmployee()),
                    logMapper.convertToDto(response.getLog())
            ));
        }
        Log.e("TAG_2", dtoList.size()+"");
        return dtoList;
    }
}
