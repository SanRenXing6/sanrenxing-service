package com.sanrenxing.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Feedback {
    private final UUID id;
    @NotBlank
    private final UUID from;
    @NotBlank
    private final UUID to;
    @NotBlank
    private final int rate;
    private final String comment;

    public Feedback(@JsonProperty UUID id,
                    @JsonProperty UUID from,
                    @JsonProperty UUID uuid,
                    @JsonProperty int rate,
                    @JsonProperty String comment) {
        this.id = id;
        this.from = from;
        to = uuid;
        this.rate = rate;
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public UUID getFrom() {
        return from;
    }

    public UUID getTo() {
        return to;
    }

    public int getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }
}
