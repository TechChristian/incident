package com.christian.incident.web.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record IncidentResponseDto(
         UUID id,

         String incident,

         String location,

         LocalDateTime createdAt
        ) {
}
