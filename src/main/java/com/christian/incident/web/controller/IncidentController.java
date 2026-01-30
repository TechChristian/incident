package com.christian.incident.web.controller;


import com.christian.incident.entity.Incident;
import com.christian.incident.service.IncidentService;
import com.christian.incident.web.dto.mapper.IncidentMapper;
import com.christian.incident.web.dto.request.IncidentCreateDto;
import com.christian.incident.web.dto.response.IncidentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/incident")
public class IncidentController {
    private final IncidentService incidentService;

    @PostMapping
    public ResponseEntity<IncidentResponseDto> createIncident (@Valid @RequestBody IncidentCreateDto incidentCreateDto){
        Incident incident = incidentService.save(IncidentMapper.toEntity(incidentCreateDto));

        IncidentResponseDto response =
                IncidentMapper.toResponseDto(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
