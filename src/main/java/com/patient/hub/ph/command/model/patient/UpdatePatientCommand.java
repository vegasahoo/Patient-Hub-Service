package com.patient.hub.ph.command.model.patient;

import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.command.vo.PatientId;
import lombok.Getter;

@Getter
public final class UpdatePatientCommand extends BaseCommand {
    private final PatientId patientId;
    private final Patient patient;

    public UpdatePatientCommand(PatientId patientId, Patient patient) {
        this.patientId = patientId;
        this.patient = patient;
    }
}
