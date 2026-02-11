package com.christian.incident.repository;

import com.christian.incident.entity.Incident;
import com.christian.incident.entity.IncidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {
    boolean existsByLocationAndStatus(String location, IncidentStatus status);
}
