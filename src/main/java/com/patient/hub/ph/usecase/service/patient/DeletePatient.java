package com.patient.hub.ph.usecase.service.patient;

import com.patient.hub.ph.command.model.patient.DeletePatientCommand;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import org.springframework.stereotype.Component;

@Component
public class DeletePatient extends PatientService<DeletePatientCommand> {

    public DeletePatient(IPatientRepo patientRepo) {
        super(patientRepo, MethodType.DELETE );
    }

    @Override
    public Object handle(DeletePatientCommand command) {
        patientRepo.deletePatientById(command.getPatientId().id());
        return "Success";
    }
}
