package com.patient.hub.ph.command.service.user;

import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.command.model.user.GetUserCommand;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.command.register.MethodType;
import com.patient.hub.ph.command.service.BaseCommandService;
import org.springframework.stereotype.Component;

@Component
public class GetUserCommandService extends BaseCommandService {

    public GetUserCommandService(){
        CommandRegister.add(MethodType.GETUSER, this);
    }
    @Override
    public BaseCommand createCommand(String... request) {
        return new GetUserCommand(request[0]);
    }
}
