package com.christian.incident.web.dto.request.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateDto(
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
) {
}
