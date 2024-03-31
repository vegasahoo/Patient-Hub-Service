package com.patient.hub.ph.usecase.service.register;

import java.util.Arrays;

public enum MethodType {

    GET,
    CREATE,
    UPDATE,
    DELETE;

    public static MethodType value(String input) {
        return Arrays.stream(MethodType.values())
                .filter(v -> v.name().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid type in eventHeader of consumed event"));
    }
}
