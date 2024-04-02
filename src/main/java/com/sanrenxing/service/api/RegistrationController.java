package com.sanrenxing.service.api;

import com.sanrenxing.service.model.request.RegistrationRequest;
import com.sanrenxing.service.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
