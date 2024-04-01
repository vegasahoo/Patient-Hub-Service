package com.patient.hub.ph.controller.api;

import com.patient.hub.ph.command.model.CreatePatientCommand;
import com.patient.hub.ph.command.model.DeletePatientCommand;
import com.patient.hub.ph.command.model.GetPatientCommand;
import com.patient.hub.ph.command.model.UpdatePatientCommand;
import com.patient.hub.ph.command.register.CommandRegister;
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
import com.patient.hub.ph.usecase.service.register.MethodType;
import com.patient.hub.ph.usecase.service.register.UsecaseRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private final PatientController patientController = new PatientController();


    @BeforeEach
    void setUp(){
        UsecaseRegister.add(MethodType.GET, getPatientService);
        UsecaseRegister.add(MethodType.CREATE, createPatientService);
        UsecaseRegister.add(MethodType.UPDATE, updatePatientService);
        UsecaseRegister.add(MethodType.DELETE, deletePatientService);
        CommandRegister.add(com.patient.hub.ph.command.register.MethodType.GET, getPatientCommandService);
        CommandRegister.add(com.patient.hub.ph.command.register.MethodType.CREATE, createPatientCommandService);
        CommandRegister.add(com.patient.hub.ph.command.register.MethodType.UPDATE, updatePatientCommandService);
        CommandRegister.add(com.patient.hub.ph.command.register.MethodType.DELETE, deletePatientCommandService);
    }


    @Test
    void getPatientDataTest(){
        Patient patient = new Patient();
        GetPatientCommand command = new GetPatientCommand(new PatientId("testId"));
        Mockito.when(getPatientCommandService.createCommand(Mockito.anyString())).thenReturn(command);
        Mockito.when(getPatientService.handle(command)).thenReturn(patient);
        Assertions.assertNotNull(patientController.getPatientData("testId"));
    }

    @Test
    void getPatientDataExceptionTest(){
        GetPatientCommand command = new GetPatientCommand(new PatientId("testId"));
        Mockito.when(getPatientCommandService.createCommand(Mockito.anyString())).thenReturn(command);
        Mockito.when(getPatientService.handle(command)).thenThrow(new MyCustomException("custom exception"));
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
        Assertions.assertEquals("Success", patientController.dischargePatient("testId"));
    }

    @Test
    void deletePatientDataTest(){
        DeletePatientCommand command = new DeletePatientCommand(new PatientId("testId"));
        Mockito.when(deletePatientCommandService.createCommand(Mockito.anyString())).thenReturn(command);
        Assertions.assertEquals("Success", patientController.deletePatientData("testId"));
    }
}
