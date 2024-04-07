package com.patient.hub.ph.command.model.user;

import com.patient.hub.ph.command.model.BaseCommand;
import lombok.Getter;

@Getter
public class GetUserCommand extends BaseCommand {
    private final String userName;

    public GetUserCommand(String userName) {
        this.userName = userName;
    }
}
