package com.patient.hub.ph.usecase.service.user;

import com.patient.hub.ph.command.model.user.GetUserCommand;
import com.patient.hub.ph.repository.userinfo.IUserDBRepo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import org.springframework.stereotype.Component;

@Component
public class GetUser extends UserService<GetUserCommand> {
    protected GetUser(IUserDBRepo userDBRepo) {
        super(userDBRepo, MethodType.GET);
    }

    @Override
    public Object handle(GetUserCommand command) {
        return userDBRepo.findByUserName(command.getUserName());
    }
}
