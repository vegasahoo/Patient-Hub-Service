package com.patient.hub.ph.command.service;

import com.patient.hub.ph.command.model.patient.DeletePatientCommand;
import com.patient.hub.ph.command.service.patient.DeletePatientCommandService;
import com.patient.hub.ph.exception.InsufficientDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePatientCommandServiceTest {

    @Mock
    private DeletePatientCommandService deletePatientCommandService;

    @Test
    void createCommandTest(){
        Mockito.when(deletePatientCommandService.createCommand(Mockito.anyString())).thenCallRealMethod();
        DeletePatientCommand command = (DeletePatientCommand) deletePatientCommandService.createCommand("testPatientId");
        Assertions.assertNotNull(command.getPatientId());
    }

    @Test
    void createCommandExceptionTest(){
        Mockito.when(deletePatientCommandService.createCommand(Mockito.anyString())).thenCallRealMethod();
        Assertions.assertThrows(InsufficientDataException.class, ()-> deletePatientCommandService.createCommand(""));
    }
}
