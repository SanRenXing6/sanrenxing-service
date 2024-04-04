package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.user.UserDao;
import com.sanrenxing.service.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE =
            "User with email %s not found";
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserDao userDao;

    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder,
                       @Qualifier("userPostgreSQL") UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public void signUpUser(User user) {
        boolean userExists = userDao.getUserByEmail(user.getEmail()).isPresent();
        if(userExists) {
            throw new IllegalStateException(String.format("Email %s already taken", user.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDao.addUser(user);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> user = userDao.getUserByEmail(email);
        return user.orElse(null);
    }
}
