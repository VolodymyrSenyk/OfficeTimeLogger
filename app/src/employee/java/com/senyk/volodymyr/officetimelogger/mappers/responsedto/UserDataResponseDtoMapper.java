package com.senyk.volodymyr.officetimelogger.mappers.responsedto;

import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.response.Employee;

public class UserDataResponseDtoMapper {
    public UserDto convertToDto(Employee response) {
        return new UserDto(
                response.getName(),
                response.getSurname(),
                response.getSecondName()
        );
    }
}
