package com.senyk.volodymyr.officetimelogger.mappers.responsedto;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.response.Log;

import java.util.ArrayList;
import java.util.List;

public class TimeLogsResponseDtoMapper {
    public List<TimeLogDto> convertToDto(List<Log> responseList) {
        List<TimeLogDto> dtoList = new ArrayList<>(responseList.size());
        for (Log response : responseList) {
            dtoList.add(new TimeLogDto(
                    Integer.parseInt(response.getId()),
                    Long.parseLong(response.getArrivalTime()) * 1000,
                    Long.parseLong(response.getLeavingTime()) * 1000,
                    Double.parseDouble(response.getTotalTime()))
            );
        }
        return dtoList;
    }
}
