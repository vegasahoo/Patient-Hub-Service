package com.patient.hub.ph.command.register;

import com.patient.hub.ph.command.service.BaseCommandService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandRegister {
    private static final Map<MethodType, BaseCommandService>
            MAPPER_SERVICE_MAP = new HashMap<>();

    public static void add(
            MethodType methodType, BaseCommandService mapper) {
        MAPPER_SERVICE_MAP.put(methodType, mapper);
    }

    public static BaseCommandService get(MethodType methodType) {
        return MAPPER_SERVICE_MAP.get(methodType);
    }

}
