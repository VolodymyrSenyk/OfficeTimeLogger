package com.senyk.volodymyr.officetimelogger.mappers.responsedto;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.response.TimeLog;

import java.util.ArrayList;
import java.util.List;

public class TimeLogsResponseDtoMapper {
    public TimeLogDto convertToDto(TimeLog response) {
        return new TimeLogDto(
                Integer.parseInt(response.getId()),
                Long.parseLong(response.getArrivalTime()) * 1000,
                Long.parseLong(response.getLeavingTime()) * 1000,
                Double.parseDouble(response.getTotalTime()));
    }

    public List<TimeLogDto> convertToDtoList(List<TimeLog> responseList) {
        List<TimeLogDto> dtoList = new ArrayList<>(responseList.size());
        for (TimeLog response : responseList) {
            dtoList.add(convertToDto(response));
        }
        return dtoList;
    }
}
