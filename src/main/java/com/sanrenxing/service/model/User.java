package com.sanrenxing.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    private final UUID id;
    @NotBlank
    private final String name;
    private final String email;
    @Enumerated(EnumType.STRING)
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
