package com.christian.incident.web.dto.mapper;

import com.christian.incident.entity.Incident;
import com.christian.incident.web.dto.IncidentDto;

import java.util.List;

import java.util.stream.Collectors;

public class IncidentMapper {
    public static Incident toEntity(IncidentDto.Create create){

        Incident incident = new Incident();
        incident.setIncident(create.incident());
        incident.setLocation(create.location());
        return incident;
    }

    //Entity -> Response DTO
    public static IncidentDto.Response toResponseDto(Incident incident) {
    /*
        UserIncidentReportDto userDto = new UserIncidentReportDto(
                incident.getUser().getUsername(),
                incident.getUser().getEmail(),
                incident.getUser().getPhone(),
                incident.getUser().getRole().name()

        );
*/
        return new IncidentDto.Response(
                incident.getId(),
                incident.getIncident(),
                incident.getLocation(),
                incident.getStatus().name(),
                incident.getCreatedAt()
        );
    }

    // Mapeamento para Listagem de Incidentes.
    public static List<IncidentDto.Response> listDto (List<Incident> incidentList){
        return incidentList
                .stream()
                .map(IncidentMapper :: toResponseDto)
                .collect(Collectors.toList());
    }
}
