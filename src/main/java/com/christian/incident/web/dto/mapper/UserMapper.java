package com.christian.incident.web.dto.mapper;
import com.christian.incident.web.dto.response.userDto.UserResponseDto;
import com.christian.incident.entity.User;

import com.christian.incident.web.dto.request.userDto.UserCreateDto;

public class UserMapper {

    public static User toEntity (UserCreateDto userCreateDto){

        User user = new User();

        user.setUsername(userCreateDto.username());
        user.setEmail(userCreateDto.email());
        user.setPassword(userCreateDto.password());
        user.setPhone(userCreateDto.phone());

        return user;
    }

    //Entity -> User Response
    public static UserResponseDto toResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getRoles()
        );
    }
}
