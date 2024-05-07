package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.user.UserDao;
import com.sanrenxing.service.model.data.UserRole;
import com.sanrenxing.service.model.data.UserStatus;
import com.sanrenxing.service.model.request.LoginRequest;
import com.sanrenxing.service.model.request.RegisterRequest;
import com.sanrenxing.service.model.data.User;
import com.sanrenxing.service.model.response.LoginResponse;
import com.sanrenxing.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userDao;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public LoginResponse register(RegisterRequest request) {
        final String email = request.getEmail();
        if (userService.checkIfUserExist(email)){
            throw new IllegalStateException("Email already existed");
        }
        var user = User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .status(UserStatus.OFFLINE)
                .build();
        userDao.addUser(user);
        return LoginResponse.builder().build();
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userDao.getUserByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .hasProfile(user.isHasProfile())
                .build();
    }
}
