package com.patient.hub.ph.command.service;

import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.command.model.DeletePatientCommand;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.command.register.MethodType;
import com.patient.hub.ph.command.vo.PatientId;
import com.patient.hub.ph.exception.InsufficientDataException;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientCommandService extends BaseCommandService {

    public DeletePatientCommandService(){
        CommandRegister.add(MethodType.DELETE, this);
    }

    @Override
    public BaseCommand createCommand(String... patientId) {
        if(patientId[0].isEmpty()){
            throw new InsufficientDataException("PatientID is invalid");
        }
        return new DeletePatientCommand(new PatientId(patientId[0]));
    }
}
