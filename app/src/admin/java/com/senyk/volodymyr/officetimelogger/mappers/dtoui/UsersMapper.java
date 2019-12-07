package com.senyk.volodymyr.officetimelogger.mappers.dtoui;

import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;

import java.util.ArrayList;
import java.util.List;

public class UsersMapper {
    public UserUi convertToUi(UserDto dto) {
        return new UserUi(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getMiddleName()
        );
    }

    public List<UserUi> convertToUiList(List<UserDto> dtoList) {
        List<UserUi> uiList = new ArrayList<>(dtoList.size());
        for (UserDto dto : dtoList) {
            uiList.add(convertToUi(dto));
        }
        return uiList;
    }
}
