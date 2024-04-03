package com.sanrenxing.service.model.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class RegistrationRequest {

    private String name;
    private String userName;
    private String password;
    private String email;
}
