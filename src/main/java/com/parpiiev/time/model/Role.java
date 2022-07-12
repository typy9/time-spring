package com.parpiiev.time.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {

    ADMIN("admin"),
    USER("user"),
    UNKNOWN("unknown");

    @Getter
    private final String value;

}
