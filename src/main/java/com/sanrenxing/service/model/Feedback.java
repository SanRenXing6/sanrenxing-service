package com.sanrenxing.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Feedback {
    private final UUID id;
    @NotBlank
    private final UUID fromUser;
    @NotBlank
    private final UUID toUser;
    @NotBlank
    private final int rate;
    private final String comment;

    public Feedback(@JsonProperty UUID id,
                    @JsonProperty UUID fromUser,
                    @JsonProperty UUID toUser,
                    @JsonProperty int rate,
                    @JsonProperty String comment) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.rate = rate;
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public UUID getFromUser() {
        return fromUser;
    }

    public UUID getToUser() {
        return toUser;
    }

    public int getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }
}
