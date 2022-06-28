package com.parpiiev.time.model;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    UNKNOWN("unknown");
    private final String value;
    Role(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
