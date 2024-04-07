package com.patient.hub.ph.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.hub.ph.command.model.patient.UpdatePatientCommand;
import com.patient.hub.ph.command.service.patient.UpdatePatientCommandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdatePatientCommandServiceTest {

    private final static String testData = "{\"age\":26,\"sex\":\"Female\",\"status\":\"UNDERGOINGTREATMENT\",\"detailsOfDisease\":\"Cough\",\"name\":\"sweta\"}";


    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UpdatePatientCommandService updatePatientCommandService = new UpdatePatientCommandService(objectMapper);

    @Test
    void createCommandTest(){
        UpdatePatientCommand command = (UpdatePatientCommand) updatePatientCommandService.createCommand("testId", testData);
        Assertions.assertNotNull(command.getPatient());
    }

    @Test
    void createCommandWithDischargeStatusTest(){
        UpdatePatientCommand command = (UpdatePatientCommand) updatePatientCommandService.createCommand("testId", null);
        Assertions.assertNotNull(command.getPatientId());
    }
}
