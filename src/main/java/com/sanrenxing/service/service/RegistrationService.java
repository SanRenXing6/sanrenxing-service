package com.sanrenxing.service.service;

import com.sanrenxing.service.model.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works";
    }
}
