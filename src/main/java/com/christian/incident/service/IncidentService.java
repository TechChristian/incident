package com.christian.incident.service;

import com.christian.incident.entity.Incident;
import com.christian.incident.entity.IncidentStatus;
import com.christian.incident.repository.IncidentRepository;
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
    public Incident save(Incident incident){
        if(incidentRepository.existsByLocationAndStatus(incident.getLocation(), IncidentStatus.OPEN)){
            throw new IllegalStateException("An open incident already exists at this location");
        }
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
