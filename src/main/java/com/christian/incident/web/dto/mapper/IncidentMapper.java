package com.christian.incident.web.dto.mapper;

import com.christian.incident.entity.Incident;
import com.christian.incident.web.dto.request.incidentDto.IncidentCreateDto;
import com.christian.incident.web.dto.response.incidentDto.IncidentResponseDto;
import com.christian.incident.web.dto.response.incidentDto.UserIncidentReportDto;

import java.util.List;

import java.util.stream.Collectors;

public class IncidentMapper {
    public static Incident toEntity(IncidentCreateDto incidentCreateDto){

        Incident incident = new Incident();
        incident.setIncident(incidentCreateDto.incident());
        incident.setLocation(incidentCreateDto.location());
        return incident;

    }

    //Entity -> Response DTO
    public static IncidentResponseDto toResponseDto(Incident incident) {

        UserIncidentReportDto userDto = new UserIncidentReportDto(
                incident.getUser().getUsername(),
                incident.getUser().getEmail(),
                incident.getUser().getPhone(),
                incident.getUser().getRoles().name()

        );

        return new IncidentResponseDto(
                incident.getId(),
                incident.getIncident(),
                incident.getLocation(),
                incident.getStatus().name(),
                incident.getCreatedAt(),
                userDto
        );
    }

    // Mapeamento para Listagem de Incidentes.
    public static List<IncidentResponseDto> listDto (List<Incident> incidentList){
        return incidentList
                .stream()
                .map(IncidentMapper :: toResponseDto)
                .collect(Collectors.toList());
    }
}
