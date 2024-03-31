package com.patient.hub.ph.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.command.model.CreatePatientCommand;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.command.register.MethodType;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.exception.MyCustomException;
import org.springframework.stereotype.Component;

@Component
public class CreatePatientCommandService extends BaseCommandService {

    private final ObjectMapper objectMapper;

    public CreatePatientCommandService(ObjectMapper objectMapper) {
        CommandRegister.add(MethodType.CREATE, this);
        this.objectMapper = objectMapper;
    }

    public CreatePatientCommand createCommand(String createPatientRequest){
        try {
            Patient patient = objectMapper.readValue(createPatientRequest, Patient.class);
            return new CreatePatientCommand(patient);
        } catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }

    @Override
    public BaseCommand createCommand(String... createPatientRequest) {
        try {
            Patient patient = objectMapper.readValue(createPatientRequest[0], Patient.class);
            return new CreatePatientCommand(patient);
        } catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }
}
