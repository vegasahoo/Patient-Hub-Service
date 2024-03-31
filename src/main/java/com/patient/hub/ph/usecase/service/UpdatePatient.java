package com.patient.hub.ph.usecase.service;


import com.patient.hub.ph.command.model.UpdatePatientCommand;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatient extends PatientService<UpdatePatientCommand>{

    public UpdatePatient(IPatientRepo patientRepo) {
        super(patientRepo, MethodType.UPDATE);
    }


    @Override
    public Object handle(UpdatePatientCommand command) {
        if(command.getPatient() == null){
            patientRepo.dischargePatient(command.getPatientId().id());
        }
        else{
            patientRepo.updatePatientData(command.getPatientId().id(), command.getPatient());
        }
        return "Success";
    }
}
