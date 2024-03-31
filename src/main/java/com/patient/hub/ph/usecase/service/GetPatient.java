package com.patient.hub.ph.usecase.service;

import com.patient.hub.ph.command.model.GetPatientCommand;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPatient extends PatientService<GetPatientCommand>{
    public GetPatient(IPatientRepo patientRepo) {
        super(patientRepo, MethodType.GET );
    }

    public List<Patient> getAllPatient(){
        return patientRepo.getAllPatient();
    }

    @Override
    public Object handle(GetPatientCommand command) {
        return patientRepo.getPatientById(command.getPatientId().id());
    }
}
