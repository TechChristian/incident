package com.christian.incident.web.controller;


import com.christian.incident.entity.Incident;
import com.christian.incident.service.IncidentService;
import com.christian.incident.web.dto.mapper.IncidentMapper;
import com.christian.incident.web.dto.request.IncidentCreateDto;
import com.christian.incident.web.dto.request.IncidentStatusUpdateDto;
import com.christian.incident.web.dto.response.IncidentResponseDto;
import com.christian.incident.web.dto.response.MessageResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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

    @GetMapping
    public ResponseEntity<List<IncidentResponseDto>> listAll(){
        List<Incident> incidents = incidentService.listIncident();
        return ResponseEntity.ok(IncidentMapper.listDto(incidents));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MessageResponseDto> updateStatus (@PathVariable UUID id, @Valid @RequestBody IncidentStatusUpdateDto dto){
       incidentService.updateStatus(id, dto.status());
        return ResponseEntity.ok(
                new MessageResponseDto("Your status has been successfully updated.")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponseDto> searchIncidentId (@PathVariable UUID id) {
        Incident incident = incidentService.searchById(id);
        return ResponseEntity.ok(IncidentMapper.toResponseDto(incident));
    }
}
