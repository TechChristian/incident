package com.christian.incident.web.dto;

import com.christian.incident.entity.enums.IncidentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public class IncidentDto {
    public record Create(

            @NotBlank(message = "It is necessary to describe the incident.")
            @Size(min = 6, max= 100, message = "The incident description must be between 6 and 100 characters.")
            String incident,

            @NotBlank(message = "It is necessary to inform the location of the incident.")
            @Size(min = 6, max = 100, message = "The location must be between 6 and 100 characters.")
            String location

    ){}

    public record StatusUpdate(
            @NotNull(message = "Status is required.")
            IncidentStatus status
    ) {}

    public record Response(
            UUID id,

            String incident,

            String location,

            String status,

            LocalDateTime createdAt

    ){}
}
