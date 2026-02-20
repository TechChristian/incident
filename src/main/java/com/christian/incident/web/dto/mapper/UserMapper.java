package com.christian.incident.web.dto.mapper;
import com.christian.incident.web.dto.UserDto;
import com.christian.incident.web.dto.response.UserResponseDto;
import com.christian.incident.entity.User;

import com.christian.incident.web.dto.request.UserCreateDto;

public class UserMapper {

    public static User toEntity (UserDto.Create userDto){

        User user = new User();

        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setPhone(userDto.phone());
        return user;
    }

    //Entity -> User Response
    public static UserDto.Response toResponseDto(User user){
        return new UserDto.Response(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }
}
