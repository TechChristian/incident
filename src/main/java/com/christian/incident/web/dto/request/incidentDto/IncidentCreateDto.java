package com.christian.incident.web.dto.request.incidentDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record IncidentCreateDto(
        @NotNull(message = "It is necessary to provide the ID to report the incident. ")
        UUID userId,

        @NotBlank(message = "It is necessary to describe the incident.")
        @Size(min = 6, max= 100, message = "The incident description must be between 6 and 100 characters.")
        String incident,

        @NotBlank(message = "It is necessary to inform the location of the incident.")
        @Size(min = 6, max = 100, message = "The location must be between 6 and 100 characters.")
        String location)  { }
