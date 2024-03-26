package com.sanrenxing.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {
    private final UUID id;
    @NotBlank
    private final String name;
    private final String email;
    private final UserStatus status;

    public User(@JsonProperty UUID id,
                @JsonProperty String name,
                @JsonProperty String email,
                @JsonProperty UserStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {return status; }
}
