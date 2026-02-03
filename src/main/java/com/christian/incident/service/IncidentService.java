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
            throw new IllegalStateException("Incident already open");
        }
        incident.setStatus(newStatus);

        return incident;
    }

    @Transactional
    public Incident save(Incident incident){
        return incidentRepository.save(incident);
    }


    @Transactional(readOnly = true)
    public List<Incident> listIncident(){
        return incidentRepository.findAll();
    }

}
