package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.user.UserDao;
import com.sanrenxing.service.model.data.ConfirmationToken;
import com.sanrenxing.service.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE =
            "User with email %s not found";
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final UserDao userDao;

    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService,
                       @Qualifier("userPostgreSQL") UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.userDao = userDao;
    }

    public String signUpUser(User user) {
        // Add new user to db
        boolean userExists = userDao.getUserByEmail(user.getEmail()).isPresent();
        if(userExists) {
            throw new IllegalStateException(String.format("Email %s already taken", user.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDao.addUser(user);

        // Add new confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                null,
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                null,
                user.getId()
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public Optional<User> getUser(UUID id) {
        return userDao.getUser(id);
    }

    public int deleteUser(UUID id) {
        return userDao.deleteUser(id);
    }

    public int updateUser(UUID id, User user) {
        return userDao.updateUser(id, user);
    }

    public void enableUser(UUID id) {userDao.enableUser(id);}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> user = userDao.getUserByEmail(email);
        if(user.isEmpty()){
            throw new IllegalStateException(String.format("User %s not found", email));
        } else{
            return user.orElse(null);
        }
    }
}
