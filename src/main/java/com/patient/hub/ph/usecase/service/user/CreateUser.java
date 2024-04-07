package com.patient.hub.ph.usecase.service.user;

import com.patient.hub.ph.command.model.user.CreateUserCommand;
import com.patient.hub.ph.repository.userinfo.IUserDBRepo;
import com.patient.hub.ph.repository.userinfo.dto.UserInfo;
import com.patient.hub.ph.usecase.service.register.MethodType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUser extends UserService<CreateUserCommand> {

    private final PasswordEncoder passwordEncoder;
    protected CreateUser(IUserDBRepo userDBRepo, PasswordEncoder passwordEncoder) {
        super(userDBRepo, MethodType.CREATE);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Object handle(CreateUserCommand command) {
        UserInfo userInfo = command.getUserInfo();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));;
        return userDBRepo.save(userInfo);
    }
}
