package com.sanrenxing.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

public class Profile {

    private final UUID id;
    @NotBlank
    private final UUID userId;
    private final String description;
    private final int rate;
    private final String needs;
    private final List<Skill> skills;
    private final List<Feedback> feedbacks;

    public Profile(@JsonProperty UUID id,
                   @JsonProperty UUID userId,
                   @JsonProperty String description,
                   @JsonProperty int rate,
                   @JsonProperty String needs,
                   @JsonProperty List<Skill> skills,
                   @JsonProperty List<Feedback> feedbacks) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.rate = rate;
        this.needs = needs;
        this.skills = skills;
        this.feedbacks = feedbacks;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public int getRate() {
        return rate;
    }

    public String getNeeds() {
        return needs;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
