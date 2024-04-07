package com.patient.hub.ph.command.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.command.model.user.CreateUserCommand;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.command.register.MethodType;
import com.patient.hub.ph.command.service.BaseCommandService;
import com.patient.hub.ph.exception.MyCustomException;
import com.patient.hub.ph.repository.userinfo.dto.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandService extends BaseCommandService {

    private final ObjectMapper objectMapper;

    public CreateUserCommandService(ObjectMapper objectMapper) {
        CommandRegister.add(MethodType.CREATEUSER, this);
        this.objectMapper = objectMapper;
    }

    @Override
    public BaseCommand createCommand(String... request) {
        try {
            UserInfo userInfo = objectMapper.readValue(request[0], UserInfo.class);
            return new CreateUserCommand(userInfo);
        } catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }
}
