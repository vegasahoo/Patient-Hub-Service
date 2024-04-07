package com.patient.hub.ph.command.service.patient;

import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.command.model.patient.GetPatientCommand;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.command.register.MethodType;
import com.patient.hub.ph.command.service.BaseCommandService;
import com.patient.hub.ph.command.vo.PatientId;
import com.patient.hub.ph.exception.InsufficientDataException;
import org.springframework.stereotype.Component;

@Component
public class GetPatientCommandService extends BaseCommandService {

    public GetPatientCommandService(){
        CommandRegister.add(MethodType.GET, this);
    }
    @Override
    public BaseCommand createCommand(String... patientId) {
        if(patientId[0].isEmpty()){
            throw new InsufficientDataException("PatientID is invalid");
        }
        return new GetPatientCommand(new PatientId(patientId[0]));
    }
}
