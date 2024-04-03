package com.sanrenxing.service.api;

import com.sanrenxing.service.model.request.RegistrationRequest;
import com.sanrenxing.service.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        System.out.println("test");

        return registrationService.register(request);
    }

}