package com.christian.incident.service;

import com.christian.incident.entity.Incident;
import com.christian.incident.entity.User;
import com.christian.incident.entity.enums.IncidentStatus;
import com.christian.incident.repository.IncidentRepository;
import com.christian.incident.repository.UserRepository;
import com.christian.incident.web.dto.IncidentDto;
import com.christian.incident.web.dto.UserDto;
import com.christian.incident.web.dto.mapper.IncidentMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Incident updateStatus(UUID id, IncidentStatus newStatus) {
        Incident incident = incidentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Incident not found")
        );

        if (incident.getStatus() == newStatus) {
            throw new IllegalStateException("An incident with this status already exists.");
        }
        incident.setStatus(newStatus);
        return incident;
    }

    @Transactional
    public Incident save(IncidentDto.Create dto){
        User user = userRepository.findById(dto.userId()).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        if(incidentRepository.existsByLocationAndStatus(dto.location(), IncidentStatus.OPEN)){
            throw new IllegalStateException("An open incident already exists at this location");
        }
        Incident incident = IncidentMapper.toEntity((dto));
        incident.setUser(user);
        return incidentRepository.save(incident);
    }

    @Transactional(readOnly = true)
    public List<Incident> listIncident(){

        return incidentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Incident searchById(UUID id) {
       return incidentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("incident not found by ID ")
        );
    }
}
