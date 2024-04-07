package com.patient.hub.ph.usecase;

import com.patient.hub.ph.command.model.patient.UpdatePatientCommand;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.command.vo.PatientId;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.patient.UpdatePatient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdatePatientTest {

    @Mock
    private IPatientRepo patientRepo;
    @InjectMocks
    private UpdatePatient updatePatient;

    @Test
    void updatePatientTest(){
        Patient patient = new Patient();
        UpdatePatientCommand updatePatientCommand = new UpdatePatientCommand(new PatientId("testId"), patient);
        UpdatePatientCommand dischargeCommand = new UpdatePatientCommand(new PatientId("testId"), null);
        Mockito.when(patientRepo.updatePatientData("testId", patient)).thenReturn(patient);
        updatePatient.handle(updatePatientCommand);
        updatePatient.handle(dischargeCommand);
        Mockito.verify(patientRepo).updatePatientData("testId",patient);
        Mockito.verify(patientRepo).dischargePatient("testId");
    }
}
