package com.christian.incident.service;
import com.christian.incident.entity.enums.Roles;
import com.christian.incident.exception.EmailAlreadyExistsException;
import com.christian.incident.web.dto.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.christian.incident.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.christian.incident.entity.User;

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
}
