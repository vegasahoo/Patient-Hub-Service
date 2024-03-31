package com.patient.hub.ph.usecase.service.register;

import com.patient.hub.ph.exception.MyCustomException;
import com.patient.hub.ph.usecase.service.PatientService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UsecaseRegister {

    private static final Map<MethodType, PatientService<?>>
            USECASE_REGISTER = new ConcurrentHashMap<>();

    private UsecaseRegister() {}

    public static void add(
            MethodType methodType, PatientService<?> usecaseProcessor) {
        USECASE_REGISTER.put(methodType, usecaseProcessor);
    }

    public static PatientService<?> get(MethodType methodType) {
        if (!USECASE_REGISTER.containsKey(methodType)) {
            throw new MyCustomException("Usecase is not registered");
        }
        return USECASE_REGISTER.get(methodType);
    }
}

