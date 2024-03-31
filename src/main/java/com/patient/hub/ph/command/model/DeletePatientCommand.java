package com.patient.hub.ph.command.model;

import com.patient.hub.ph.command.vo.PatientId;
import lombok.Getter;

@Getter
public final class DeletePatientCommand extends BaseCommand {
    private final PatientId patientId;

    public DeletePatientCommand(PatientId patientId) {
        this.patientId = patientId;
    }
}
