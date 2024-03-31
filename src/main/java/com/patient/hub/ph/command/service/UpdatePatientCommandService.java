package com.patient.hub.ph.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.command.model.UpdatePatientCommand;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.command.register.MethodType;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.command.vo.PatientId;
import com.patient.hub.ph.exception.InsufficientDataException;
import com.patient.hub.ph.exception.MyCustomException;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientCommandService extends BaseCommandService {

    private final ObjectMapper objectMapper;

    public UpdatePatientCommandService(ObjectMapper objectMapper) {
        CommandRegister.add(MethodType.UPDATE, this);
        this.objectMapper = objectMapper;
    }

    @Override
    public BaseCommand createCommand(String... request) {
        try {
            String data = null;
            String id = request[0];
            if(request.length >1){
                data = request[1];
            }
            if(id.isEmpty()){
                throw new InsufficientDataException("PatientId cannot be empty");
            }
            PatientId patientId = new PatientId(id);
            if(data != null){
                Patient patient = objectMapper.readValue(data, Patient.class);
                return new UpdatePatientCommand(patientId, patient);
            }
            return new UpdatePatientCommand(patientId, null);
        } catch (JsonProcessingException e) {
            throw new MyCustomException("JSON Parsing error occurred");
        }
    }
}
