package com.patient.hub.ph.usecase.service.patient;

import com.patient.hub.ph.usecase.port.IPatientRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import com.patient.hub.ph.usecase.service.register.PatientUseCaseRegister;

public abstract class PatientService<T> {
    protected final IPatientRepo patientRepo;

    protected PatientService(IPatientRepo patientRepo, MethodType methodType) {
        PatientUseCaseRegister.add(methodType, this);
        this.patientRepo = patientRepo;
    }

    public abstract Object handle(T command);
}
