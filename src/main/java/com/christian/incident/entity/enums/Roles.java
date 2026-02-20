package com.christian.incident.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    ADMIN("admin"),
    USER("user");

    private final String role;

    Roles(String role){
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static Roles from(String value){
        return Roles.valueOf(value.toUpperCase());
    }
}
