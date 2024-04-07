package com.patient.hub.ph.usecase.service.register;

import com.patient.hub.ph.exception.MyCustomException;
import com.patient.hub.ph.usecase.service.user.UserService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserUseCaseRegister {
    private static final Map<MethodType, UserService<?>>
            USECASE_REGISTER = new ConcurrentHashMap<>();

    private UserUseCaseRegister() {}

    public static void add(
            MethodType methodType, UserService<?> usecaseProcessor) {
        USECASE_REGISTER.put(methodType, usecaseProcessor);
    }

    public static UserService<?> get(MethodType methodType) {
        if (!USECASE_REGISTER.containsKey(methodType)) {
            throw new MyCustomException("Usecase is not registered");
        }
        return USECASE_REGISTER.get(methodType);
    }
}
