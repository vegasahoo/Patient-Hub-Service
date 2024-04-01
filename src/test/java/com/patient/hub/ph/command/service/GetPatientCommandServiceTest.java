package com.patient.hub.ph.command.service;

import com.patient.hub.ph.command.model.GetPatientCommand;
import com.patient.hub.ph.exception.InsufficientDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetPatientCommandServiceTest {

    @Mock
    private GetPatientCommandService getPatientCommandService;

    @Test
    void createCommandTest(){
        Mockito.when(getPatientCommandService.createCommand(Mockito.anyString())).thenCallRealMethod();
        GetPatientCommand command = (GetPatientCommand) getPatientCommandService.createCommand("testPatientId");
        Assertions.assertNotNull(command.getPatientId());
    }

    @Test
    void createCommandExceptionTest(){
        Mockito.when(getPatientCommandService.createCommand(Mockito.anyString())).thenCallRealMethod();
        Assertions.assertThrows(InsufficientDataException.class, ()-> getPatientCommandService.createCommand(""));
    }

}
