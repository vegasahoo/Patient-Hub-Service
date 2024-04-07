package com.patient.hub.ph.usecase.service.user;

import com.patient.hub.ph.repository.userinfo.IUserDBRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import com.patient.hub.ph.usecase.service.register.UserUseCaseRegister;

public abstract class UserService<T> {
    protected final IUserDBRepo userDBRepo;


    protected UserService(IUserDBRepo userDBRepo, MethodType methodType) {
        UserUseCaseRegister.add(methodType, this);
        this.userDBRepo = userDBRepo;
    }

    public abstract Object handle(T command);
}
