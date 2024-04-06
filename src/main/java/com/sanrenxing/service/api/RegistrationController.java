package com.sanrenxing.service.api;

import com.sanrenxing.service.exception.GlobalExceptionHandler;
import com.sanrenxing.service.model.request.RegistrationRequest;
import com.sanrenxing.service.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    @Autowired
    private final GlobalExceptionHandler globalExceptionHandler;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        String token = registrationService.register(request);
        return ResponseEntity.ok(String.format("User %s registered, token: %s", request.getUserName(), token));
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Delegate to GlobalExceptionHandler for handling
        return globalExceptionHandler.handleException(ex);
    }
}