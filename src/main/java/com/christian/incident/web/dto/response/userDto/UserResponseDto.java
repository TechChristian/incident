package com.christian.incident.web.dto.response.userDto;
import com.christian.incident.entity.enums.Roles;

import java.util.UUID;

public record UserResponseDto(
        UUID id,

        String username,

        String email,

        String phone,

        Roles role

) {
}
