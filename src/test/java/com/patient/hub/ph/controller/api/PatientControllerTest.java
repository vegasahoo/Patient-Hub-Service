package com.patient.hub.ph.controller.api;

import com.patient.hub.ph.command.model.CreatePatientCommand;
import com.patient.hub.ph.command.model.DeletePatientCommand;
import com.patient.hub.ph.command.model.GetPatientCommand;
import com.patient.hub.ph.command.model.UpdatePatientCommand;
import com.patient.hub.ph.command.service.CreatePatientCommandService;
import com.patient.hub.ph.command.service.DeletePatientCommandService;
import com.patient.hub.ph.command.service.GetPatientCommandService;
import com.patient.hub.ph.command.service.UpdatePatientCommandService;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.command.vo.PatientId;
import com.patient.hub.ph.exception.MyCustomException;
import com.patient.hub.ph.usecase.service.CreatePatient;
import com.patient.hub.ph.usecase.service.DeletePatient;
import com.patient.hub.ph.usecase.service.GetPatient;
import com.patient.hub.ph.usecase.service.UpdatePatient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private GetPatientCommandService getPatientCommandService;
    @Mock
    private UpdatePatientCommandService updatePatientCommandService;
    @Mock
    private CreatePatientCommandService createPatientCommandService;
    @Mock
    private DeletePatientCommandService deletePatientCommandService;

    @Mock
    private GetPatient getPatientService;
    @Mock
    private CreatePatient createPatientService;
    @Mock
    private UpdatePatient updatePatientService;
    @Mock
    private DeletePatient deletePatientService;

    @InjectMocks
    private PatientController patientController;

    @Test
    void getPatientDataTest(){
        Patient patient = new Patient();
        GetPatientCommand command = new GetPatientCommand(new PatientId("testId"));
        Mockito.when(getPatientCommandService.createCommand(Mockito.anyString())).thenReturn(command);
        Mockito.when(getPatientService.getPatientById(command)).thenReturn(patient);
        Assertions.assertNotNull(patientController.getPatientData("testId"));
    }

    @Test
    void getPatientDataExceptionTest(){
        GetPatientCommand command = new GetPatientCommand(new PatientId("testId"));
        Mockito.when(getPatientCommandService.createCommand(Mockito.anyString())).thenReturn(command);
        Mockito.when(getPatientService.getPatientById(command)).thenThrow(new MyCustomException("custom exception"));
        Assertions.assertThrows(MyCustomException.class, ()-> patientController.getPatientData("testId"));
    }

    @Test
    void getAllPatientDataTest(){
        Patient patient = new Patient();
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        Mockito.when(getPatientService.getAllPatient()).thenReturn(patientList);
        Assertions.assertNotNull(patientController.getAllPatient());
    }


    @Test
    void registerPatientTest(){
        Patient patient = new Patient();
        CreatePatientCommand command = new CreatePatientCommand(patient);
        Mockito.when(createPatientCommandService.createCommand(Mockito.anyString())).thenReturn(command);
        Assertions.assertEquals("Success", patientController.registerPatient("testId"));
    }


    @Test
    void updatePatientDataTest(){
        Patient patient = new Patient();
        UpdatePatientCommand command = new UpdatePatientCommand(new PatientId("testId"), patient);
        Mockito.when(updatePatientCommandService.createCommand(Mockito.any(), Mockito.any())).thenReturn(command);
        Assertions.assertEquals("Success", patientController.updatePatientData("testId", "testData"));
    }

    @Test
    void dischargePatientTest(){Patient patient = new Patient();
        UpdatePatientCommand command = new UpdatePatientCommand(new PatientId("testId"), patient);
        Mockito.when(updatePatientCommandService.createCommand(Mockito.any(), Mockito.any())).thenReturn(command);
        Assertions.assertEquals("Success", patientController.dischargePatient("testId"));
    }

    @Test
    void deletePatientDataTest(){
        DeletePatientCommand command = new DeletePatientCommand(new PatientId("testId"));
        Mockito.when(deletePatientCommandService.createCommand(Mockito.anyString())).thenReturn(command);
        Assertions.assertEquals("Success", patientController.deletePatientData("testId"));
    }

}
