package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.UserDao;
import com.sanrenxing.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("postgres") UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user){
        return userDao.addUser(user);
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
}
