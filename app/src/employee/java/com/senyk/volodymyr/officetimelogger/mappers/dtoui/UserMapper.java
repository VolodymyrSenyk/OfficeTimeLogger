package com.senyk.volodymyr.officetimelogger.mappers.dtoui;

import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;

public class UserMapper {
    public UserUi convertToUi(UserDto dto) {
        return new UserUi(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getMiddleName());
    }
}
