package com.senyk.volodymyr.officetimelogger.mappers.responsedto;

import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.response.Employee;

import java.util.ArrayList;
import java.util.List;

public class UsersResponseDtoMapper {
    public UserDto convertToDto(Employee employee) {
        return new UserDto(
                Integer.parseInt(employee.getPersonalNumber()),
                employee.getName(),
                employee.getSurname(),
                employee.getSecondName()
        );
    }

    public List<UserDto> convertToDtoList(List<Employee> responseList) {
        List<UserDto> dtoList = new ArrayList<>(responseList.size());
        for (Employee response : responseList) {
            dtoList.add(convertToDto(response));
        }
        return dtoList;
    }
}
