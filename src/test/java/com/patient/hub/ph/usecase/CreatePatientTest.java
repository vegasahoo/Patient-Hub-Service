package com.patient.hub.ph.usecase;

import com.patient.hub.ph.command.model.CreatePatientCommand;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.CreatePatient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePatientTest {

    @Mock
    private IPatientRepo patientRepo;
    @InjectMocks
    private CreatePatient createPatient;

    @Test
    void createPatientTest(){
        Patient patient = new Patient();
        CreatePatientCommand command = new CreatePatientCommand(patient);
        Mockito.when(patientRepo.registerPatient(patient)).thenReturn(patient);
        createPatient.handle(command);
        Mockito.verify(patientRepo).registerPatient(patient);
    }
}
