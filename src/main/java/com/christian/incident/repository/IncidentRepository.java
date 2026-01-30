package com.christian.incident.repository;

import com.christian.incident.entity.Incident;
import com.christian.incident.entity.IncidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, IncidentStatus> {
}
