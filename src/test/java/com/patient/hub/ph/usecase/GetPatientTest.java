package com.patient.hub.ph.usecase;

import com.patient.hub.ph.command.model.GetPatientCommand;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.command.vo.PatientId;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.GetPatient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith({MockitoExtension.class})
class GetPatientTest {

    @Mock
    private IPatientRepo patientRepo;


    @InjectMocks
    private GetPatient getPatient;

    @Test
    void getPatientByIdTest(){
        Patient patient = new Patient();
        GetPatientCommand getPatientCommand = new GetPatientCommand(new PatientId("testId"));
        Mockito.when(patientRepo.getPatientById("testId")).thenReturn(patient);
        Assertions.assertNotNull(getPatient.getPatientById(getPatientCommand));
    }

    @Test
    void getAllPatientTest(){
        Patient patient = new Patient();
        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        Mockito.when(patientRepo.getAllPatient()).thenReturn(patientList);
        Assertions.assertNotNull(getPatient.getAllPatient());
    }
}
