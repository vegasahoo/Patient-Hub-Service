package com.patient.hub.ph.usecase.service;


import com.patient.hub.ph.command.model.CreatePatientCommand;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import org.springframework.stereotype.Component;

@Component
public class CreatePatient extends PatientService<CreatePatientCommand> {

    public CreatePatient(IPatientRepo patientRepo) {
        super(patientRepo, MethodType.CREATE );
    }

    @Override
    public Object handle(CreatePatientCommand command) {
        patientRepo.registerPatient(command.getPatient());
        return "Success";
    }
}
