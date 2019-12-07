package com.senyk.volodymyr.officetimelogger.mappers.dtoui;

import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;

import java.util.ArrayList;
import java.util.List;

public class TimeLogsMapper {
    private final ResourcesProvider resourcesProvider;

    public TimeLogsMapper(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    public TimeLogUi convertToUi(TimeLogDto dto) {
        return new TimeLogUi(
                dto.getId(),
                resourcesProvider.getDay(dto.getArrivalTime()),
                resourcesProvider.getTime(dto.getArrivalTime()),
                resourcesProvider.getTime(dto.getLeavingTime()),
                resourcesProvider.getTotalTime(dto.getTotalTime())
        );
    }

    public List<TimeLogUi> convertToUiList(List<TimeLogDto> dtoList) {
        List<TimeLogUi> uiList = new ArrayList<>(dtoList.size());
        for (TimeLogDto dto : dtoList) {
            uiList.add(convertToUi(dto));
        }
        return uiList;
    }
}
