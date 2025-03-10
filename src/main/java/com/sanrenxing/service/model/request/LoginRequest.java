package com.sanrenxing.service.model.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String email;
    private String password;
}
