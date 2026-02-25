package com.christian.incident.web.controller;


import com.christian.incident.entity.User;
import com.christian.incident.service.UserService;
import com.christian.incident.web.dto.MessageResponseDto;
import com.christian.incident.web.dto.UserDto;
import com.christian.incident.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<UserDto.Response>> listAll(){
        List<User> users = userService.listUser();
        return
                ResponseEntity.ok(UserMapper.listDto(users));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<MessageResponseDto> updateInfo (@PathVariable UUID id, @Valid @RequestBody UserDto.Update dto){
        userService.updatePartial(id, dto);
        return ResponseEntity.ok(
                new MessageResponseDto(
                        "Your info has been sucessfullly updated."
                ));
    }
}
