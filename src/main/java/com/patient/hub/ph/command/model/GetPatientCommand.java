package com.patient.hub.ph.command.model;

import com.patient.hub.ph.command.vo.PatientId;
import lombok.Getter;

@Getter
public final class GetPatientCommand extends BaseCommand {
    private final PatientId patientId;

    public GetPatientCommand(PatientId patientId) {
        this.patientId = patientId;
    }
}
