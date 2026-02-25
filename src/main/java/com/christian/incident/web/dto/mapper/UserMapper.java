package com.christian.incident.web.dto.mapper;
import com.christian.incident.web.dto.UserDto;
import com.christian.incident.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity (UserDto.Create dto){

        User user = new User();

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setPhone(dto.phone());
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

    public static List<UserDto.Response> listDto(List<User> userList){
        return userList
                .stream()
                .map(UserMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
