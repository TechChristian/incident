package com.christian.incident.web.controller;


import com.christian.incident.entity.User;
import com.christian.incident.service.UserService;
import com.christian.incident.web.dto.UserDto;
import com.christian.incident.web.dto.mapper.UserMapper;
import com.christian.incident.web.dto.request.UserCreateDto;
import com.christian.incident.web.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto.Response> createUser (@Valid @RequestBody UserDto.Create userCreate ){
        User user = userService.save(UserMapper.toEntity(userCreate));

        UserDto.Response responseDto=
                UserMapper.toResponseDto(user);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
