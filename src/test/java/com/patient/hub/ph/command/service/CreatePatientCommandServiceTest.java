package com.patient.hub.ph.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.hub.ph.command.model.CreatePatientCommand;
import com.patient.hub.ph.exception.MyCustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePatientCommandServiceTest {

    private static final String testData = "{\"patientId\":\"8\",\"age\":25,\"sex\":\"Male\",\"mobile\":9777791775,\"locality\":\"bangalore\",\"status\":\"ADMITTED\",\"detailsOfDisease\":\"Fever\",\"name\":\"patient1\"}";
    private static final String wrongTestData = "{\"patientId\":\"8\",\"age\":25,\"sex\":\"Male\",\"mobile\":9777791775,\"locality\":\"bangalore\",\"status\":\"NEW\",\"detailsOfDisease\":\"Fever\",\"name\":\"patient1\"}";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CreatePatientCommandService createPatientCommandService = new CreatePatientCommandService(objectMapper);

    @Test
    void createCommandTest(){
        CreatePatientCommand command = (CreatePatientCommand) createPatientCommandService.createCommand(testData);
        Assertions.assertNotNull(command.getPatient());
    }

    @Test
    void createCommandExceptionTest(){
        Assertions.assertThrows(MyCustomException.class, ()-> createPatientCommandService.createCommand(wrongTestData));
    }
}
