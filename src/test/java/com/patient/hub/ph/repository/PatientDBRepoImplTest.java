package com.patient.hub.ph.repository;

import com.patient.hub.ph.command.vo.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith({MockitoExtension.class})
class PatientDBRepoImplTest {
    @Mock
    private IPatientDBRepo patientDBRepo;
    @InjectMocks
    private PatientDBRepoImpl patientDBRepoImpl;

    @Test
    void getPatientByIdTest(){
        Patient patient = new Patient();
        Mockito.when(patientDBRepo.findById("testId")).thenReturn(Optional.of(patient));
        Assertions.assertNotNull(patientDBRepoImpl.getPatientById("testId"));
    }

    @Test
    void getAllPatientTest(){
        Patient patient = new Patient();
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        Mockito.when(patientDBRepo.findAll()).thenReturn(patientList);
        Assertions.assertNotNull(patientDBRepoImpl.getAllPatient());
    }

    @Test
    void registerPatientTest(){
        Patient patient = new Patient();
        Mockito.when(patientDBRepo.save(patient)).thenReturn(patient);
        Assertions.assertNotNull(patientDBRepoImpl.registerPatient(patient));
    }

    @Test
    void updatePatientDataTest(){
        Patient patient = new Patient();
        Mockito.when(patientDBRepo.findById("testId")).thenReturn(Optional.of(patient));
        Mockito.when(patientDBRepo.save(patient)).thenReturn(patient);
        Assertions.assertNotNull(patientDBRepoImpl.updatePatientData("testId",patient));
    }

    @Test
    void dischargePatientTest(){
        Patient patient = new Patient();
        Mockito.when(patientDBRepo.findById("testId")).thenReturn(Optional.of(patient));
        Mockito.when(patientDBRepo.save(patient)).thenReturn(patient);
        Assertions.assertNotNull(patientDBRepoImpl.dischargePatient("testId"));
    }

    @Test
    void deletePatientByIdTest(){
        patientDBRepoImpl.deletePatientById("testId");
        Mockito.verify(patientDBRepo).deleteById("testId");
    }
}
