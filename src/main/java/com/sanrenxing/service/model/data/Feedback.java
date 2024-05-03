package com.sanrenxing.service.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Feedback {
    private final UUID id;
    @NotBlank
    private final UUID fromUser;
    @NotBlank
    private final UUID toUser;
    @NotBlank
    private final int rate;
    private final String comment;
}
