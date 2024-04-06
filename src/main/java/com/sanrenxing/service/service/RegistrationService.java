package com.sanrenxing.service.service;

import com.sanrenxing.service.common.EmailValidator;
import com.sanrenxing.service.model.data.ConfirmationToken;
import com.sanrenxing.service.model.data.User;
import com.sanrenxing.service.model.data.UserRole;
import com.sanrenxing.service.model.data.UserStatus;
import com.sanrenxing.service.model.request.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        // Check if email is valid
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException(String.format("Email %s not valid", request.getEmail()));
        }
        return userService.signUpUser(new User(
                UUID.randomUUID(),
                request.getName(),
                request.getUserName(),
                request.getPassword(),
                request.getEmail(),
                UserStatus.OFFLINE,
                UserRole.USER,
                false,
                false
        ));
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getConfirmationToken(token)
                .orElseThrow(()-> new IllegalStateException("Token not found"));
        if(confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        if(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUserId());
        return "Confirmed";
    }
}
