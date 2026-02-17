package com.christian.incident.web.dto.response.incidentDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record IncidentResponseDto(
         UUID id,

         String incident,

         String location,

         String status,

         LocalDateTime createdAt,

         UserIncidentReportDto user

        ) {
}
