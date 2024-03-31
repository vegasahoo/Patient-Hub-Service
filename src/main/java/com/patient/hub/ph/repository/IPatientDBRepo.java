package com.patient.hub.ph.repository;

import com.patient.hub.ph.command.vo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IPatientDBRepo extends JpaRepository<Patient, String> {
}
