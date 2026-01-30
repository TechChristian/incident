package com.christian.incident.service;

import com.christian.incident.entity.Incident;
import com.christian.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class IncidentService {
    private final IncidentRepository incidentRepository;

    @Transactional
    public Incident save(Incident incident){
        return incidentRepository.save(incident);
    }
}
