package com.christian.incident.service;
import com.christian.incident.entity.enums.Roles;
import com.christian.incident.exception.EmailAlreadyExistsException;
import com.christian.incident.web.dto.UserDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.christian.incident.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.christian.incident.entity.User;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User save(User user){

    if(userRepository.existsByEmail(user.getEmail())){
        throw new EmailAlreadyExistsException("Email already registered!");
    }
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> listUser(){
        return
                userRepository.findAll();
    }

    @Transactional
    public User updatePartial(UUID id, UserDto.Update dto){
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user not found")
        );
        if(dto.phone()!=null){
            user.setPhone(dto.phone());
        }
        if(dto.email() != null){
            user.setEmail(dto.email());
        }
            return user;
    }
}
