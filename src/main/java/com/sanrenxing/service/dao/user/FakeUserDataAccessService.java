package com.sanrenxing.service.dao.user;

import com.sanrenxing.service.model.data.User;
import com.sanrenxing.service.model.data.UserRole;
import com.sanrenxing.service.model.data.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int addUser(User user) {
        UUID id = UUID.randomUUID();
        DB.add(new User(id,
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                UserStatus.ACTIVE,
                UserRole.USER,
                false,
                true));
        return 1;
    }

    @Override
    public List<User> getAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> getUser(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUser(UUID id) {
        Optional<User> user = getUser(id);
        if(user.isEmpty()) return 0;
        DB.remove(user.get());
        return 1;
    }

    @Override
    public int updateUser(UUID id, User user) {
        return getUser(id)
                .map(u -> {
                    int indexOfUser = DB.indexOf(u);
                    if(indexOfUser >= 0) {
                        DB.set(indexOfUser, user);
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return DB.stream().filter(item -> item.getEmail().equals(email)).findFirst();
    }

    @Override
    public void enableUser(UUID id) {}
}
