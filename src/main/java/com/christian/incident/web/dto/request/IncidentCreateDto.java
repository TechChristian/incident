package com.christian.incident.web.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record IncidentCreateDto(

        @NotBlank(message = "It is necessary to describe the incident.")
        @Size(min = 6, max= 100, message = "The incident description must be between 6 and 100 characters.")
        String incident,

        @NotBlank(message = "It is necessary to inform the location of the incident.")
        @Size(min = 6, max = 100, message = "The location must be between 6 and 100 characters.")
        String location)  { }
