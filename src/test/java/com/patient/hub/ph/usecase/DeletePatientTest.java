package com.patient.hub.ph.usecase;

import com.patient.hub.ph.command.model.DeletePatientCommand;
import com.patient.hub.ph.command.vo.PatientId;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.DeletePatient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePatientTest {
    @Mock
    private IPatientRepo patientRepo;
    @InjectMocks
    private DeletePatient deletePatient;

    @Test
    void deletePatientById(){
        DeletePatientCommand command = new DeletePatientCommand(new PatientId("testId"));
        deletePatient.deletePatientById(command);
        Mockito.verify(patientRepo).deletePatientById("testId");
    }

}
