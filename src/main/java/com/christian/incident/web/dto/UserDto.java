package com.christian.incident.web.dto;

import com.christian.incident.entity.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UserDto {
    
    public record Create (
            @NotBlank(message = "Username cannot be blank.")
            String username,

            @NotBlank
            @Email(message = "invalid email format", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
            String email,

            @NotBlank(message = "Password cannot be blank.")
            @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters.")
           String password,
            @NotBlank(message = "Phone number cannot be blank.")
            @Size(min = 10, max = 13, message = "Phone must be between 10 and 13 characters.")
            String phone
    ){}

    public record Response(
            UUID id,

            String username,

            String email,

            String phone,

            Roles role
    ) {
    }
    public record Update(
            String phone,
            String email
    ){}
}
