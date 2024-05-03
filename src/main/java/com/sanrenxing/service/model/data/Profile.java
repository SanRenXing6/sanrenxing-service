package com.sanrenxing.service.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Profile {

    private final UUID id;
    @NotBlank
    private final UUID userId;
    private final String description;
    private final UUID imageId;
    private final int rate;
    private final String needs;
    private final List<Skill> skills;
    private final List<Feedback> feedbacks;
}
