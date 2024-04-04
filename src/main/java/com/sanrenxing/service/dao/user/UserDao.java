package com.sanrenxing.service.dao.user;

import com.sanrenxing.service.model.data.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    int addUser(User user);

    List<User> getAllUsers();

    Optional<User> getUser(UUID id);

    int deleteUser(UUID id);

    int updateUser(UUID id, User user);

    Optional<User> getUserByEmail(String email);
}
