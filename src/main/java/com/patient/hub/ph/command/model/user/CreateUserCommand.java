package com.patient.hub.ph.command.model.user;

import com.patient.hub.ph.command.model.BaseCommand;
import com.patient.hub.ph.repository.userinfo.dto.UserInfo;
import lombok.Getter;

@Getter
public class CreateUserCommand extends BaseCommand {

    private final UserInfo userInfo;

    public CreateUserCommand(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
