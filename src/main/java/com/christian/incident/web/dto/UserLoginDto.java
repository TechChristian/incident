package com.christian.incident.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDto(
        @NotBlank(message = "Username cannot be blank.")
        String username,

        @NotBlank(message = "Password cannot be blank.")
        @Size(min = 6, max = 10, message = "Your password is invalid.")
        String password
) {
}