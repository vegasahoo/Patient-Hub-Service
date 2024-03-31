package com.patient.hub.ph.usecase.service;

import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import com.patient.hub.ph.usecase.service.register.UsecaseRegister;

import java.util.List;

public abstract class PatientService<T> {
    protected final IPatientRepo patientRepo;

    protected PatientService(IPatientRepo patientRepo, MethodType methodType) {
        UsecaseRegister.add(methodType, this);
        this.patientRepo = patientRepo;
    }

    public abstract Object handle(T command);
}
