package com.patient.hub.ph.command.model;

import com.patient.hub.ph.command.vo.Patient;
import lombok.Getter;

@Getter
public class CreatePatientCommand extends BaseCommand {
    private final Patient patient;

    public CreatePatientCommand(Patient patient) {
        this.patient = patient;
    }
}
