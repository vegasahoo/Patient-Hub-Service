package com.patient.hub.ph.usecase.port;

import com.patient.hub.ph.command.vo.Patient;

import java.util.List;

public interface IPatientRepo {
    Patient getPatientById(String patientId);
    List<Patient> getAllPatient();
    Patient registerPatient(Patient patient);
    Patient updatePatientData(String patientId, Patient patient);
    Patient dischargePatient(String patientId);
    void deletePatientById(String patientId);


}
