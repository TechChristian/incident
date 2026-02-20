package com.christian.incident.web.dto.request;

import com.christian.incident.entity.enums.IncidentStatus;
import jakarta.validation.constraints.NotNull;

public record IncidentStatusUpdateDto(
    @NotNull(message = "Status is required.")
    IncidentStatus status
) {
}
