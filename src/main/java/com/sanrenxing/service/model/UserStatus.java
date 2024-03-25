package com.sanrenxing.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    @JsonProperty("OFFLINE")
    OFFLINE,
    @JsonProperty("ACTIVE")
    ACTIVE;

    @JsonValue
    public String getValue() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static UserStatus fromValue(String value) {
        for (UserStatus status: UserStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid UserStatus value: " + value);
    }
}
