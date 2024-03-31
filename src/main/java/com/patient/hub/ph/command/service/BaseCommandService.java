package com.patient.hub.ph.command.service;

import com.patient.hub.ph.command.model.BaseCommand;

public abstract class BaseCommandService {
    public abstract BaseCommand createCommand(String... request);
}
