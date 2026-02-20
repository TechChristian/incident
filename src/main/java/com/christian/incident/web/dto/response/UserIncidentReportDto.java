package com.christian.incident.web.dto.response;

public record UserIncidentReportDto(
        String username,

        String email,

        String phone,

        String roles
) {
}
