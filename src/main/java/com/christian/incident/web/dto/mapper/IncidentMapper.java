package com.christian.incident.web.dto.mapper;

import com.christian.incident.entity.Incident;
import com.christian.incident.web.dto.request.IncidentCreateDto;
import com.christian.incident.web.dto.response.IncidentResponseDto;

public class IncidentMapper {
    public static Incident toEntity(IncidentCreateDto incidentCreateDto){
        Incident incident = new Incident();
        incident.setIncident(incidentCreateDto.incident());
        incident.setLocation(incidentCreateDto.location());
        return incident;
    }

    //Entity -> Response DTO
    public static IncidentResponseDto toResponseDto(Incident incident) {
        return new IncidentResponseDto(
                incident.getId(),
                incident.getIncident(),
                incident.getLocation(),
                incident.getCreatedAt()
        );
    }

}
