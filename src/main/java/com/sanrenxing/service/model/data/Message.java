package com.sanrenxing.service.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    private final UUID id;
    @NotBlank
    private final UUID fromUserId;
    @NotBlank
    private final String fromUserName;
    @NotBlank
    private final UUID toUserId;
    @NotBlank
    private final String toUserName;
    @NotBlank
    private final String content;
    private LocalDateTime createdAt;

}
