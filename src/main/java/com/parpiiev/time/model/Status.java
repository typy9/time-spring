package com.parpiiev.time.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {
    CREATED("CREATED"),
    APPROVED("APPROVED"),
    DECLINED("DECLINED"),
    TOBEDELETED("TOBEDELETED");

    @Getter
    private final String value;
}
