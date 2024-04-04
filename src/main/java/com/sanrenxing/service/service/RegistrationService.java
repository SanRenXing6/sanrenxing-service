package com.sanrenxing.service.service;

import com.sanrenxing.service.common.EmailValidator;
import com.sanrenxing.service.model.data.User;
import com.sanrenxing.service.model.data.UserRole;
import com.sanrenxing.service.model.data.UserStatus;
import com.sanrenxing.service.model.request.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    public void register(RegistrationRequest request) {
        // Check if email is valid
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException(String.format("Email %s not valid", request.getEmail()));
        }
        userService.signUpUser(new User(
                UUID.randomUUID(),
                request.getName(),
                request.getUserName(),
                request.getPassword(),
                request.getEmail(),
                UserStatus.OFFLINE,
                UserRole.USER,
                false,
                true
        ));
    }
}
