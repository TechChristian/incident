package com.christian.incident.web.controller;


import com.christian.incident.entity.Incident;
import com.christian.incident.service.IncidentService;
import com.christian.incident.web.dto.mapper.IncidentMapper;
import com.christian.incident.web.dto.request.incidentDto.IncidentCreateDto;
import com.christian.incident.web.dto.request.incidentDto.IncidentStatusUpdateDto;
import com.christian.incident.web.dto.response.incidentDto.IncidentResponseDto;
import com.christian.incident.web.dto.response.MessageResponseDto;
import com.christian.incident.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @Operation(
            summary = "Criar um novo Incidente",
            description = "Recurso para criar um novo incidente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Incidente criado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidentResponseDto.class))),

                    @ApiResponse(responseCode = "500", description = "Incidente já aberto neste local.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "422", description = "Recurso não processado - dados inválidos.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )

    @PostMapping
    public ResponseEntity<IncidentResponseDto> createIncident (@Valid @RequestBody IncidentCreateDto incidentCreateDto){
        Incident incident = incidentService.save(IncidentMapper.toEntity(incidentCreateDto));
        IncidentResponseDto response =
                IncidentMapper.toResponseDto(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation (
            summary = "Listar todos os Incidentes",
            description = "Recurso Para Listar Incidentes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Incidentes listados Com sucesso.", content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = IncidentResponseDto.class)))),
    }
    )
    @GetMapping
    public ResponseEntity<List<IncidentResponseDto>> listAll(){
        List<Incident> incidents = incidentService.listIncident();
        return ResponseEntity.ok(IncidentMapper.listDto(incidents));
    }

    @Operation (
            summary = "Atualizar Status do Incidente",
            description = "Recurso para atualizar status do incidente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status Atualizado com Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidentResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Já existe um incidente com esse status", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidentResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "status de incidente não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidentResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Status Invalido, os unicos status permitidos são : OPEN, IN_PROGRESS, RESOLVED", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidentResponseDto.class)))
            }
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<MessageResponseDto> updateStatus (@PathVariable UUID id, @Valid @RequestBody IncidentStatusUpdateDto dto){
       incidentService.updateStatus(id, dto.status());
        return ResponseEntity.ok(
                new MessageResponseDto("Your status has been successfully updated.")
        );
    }

    @Operation(
            summary = "Buscar Incidente pelo ID",
            description = "Recurso para buscar incidente pelo ID",
            responses =  {
                    @ApiResponse(responseCode = "200", description = "Incidente listado com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema (schema = @Schema(implementation = IncidentResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "Incidente não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidentResponseDto.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponseDto> searchIncidentId (@PathVariable UUID id) {
        Incident incident = incidentService.searchById(id);
        return ResponseEntity.ok(IncidentMapper.toResponseDto(incident));
    }
}
