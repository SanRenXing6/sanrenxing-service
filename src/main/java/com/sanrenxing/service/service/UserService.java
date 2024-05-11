package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.user.UserDao;
import com.sanrenxing.service.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("userMySQL") UserDao userDao) {
        this.userDao = userDao;
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

    public int updateUserProfile(UUID id, boolean hasProfile) {
        return userDao.updateUserProfile(id, hasProfile);
    }

    public boolean checkIfUserExist(String email) {
        try {
            loadUserByUsername(email);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> user = userDao.getUserByEmail(email);
        if(user.isEmpty()){
            throw new IllegalStateException("User not found");
        } else{
            return user.orElse(null);
        }
    }
}
