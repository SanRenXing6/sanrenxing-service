package com.sanrenxing.service.dao.user;

import com.sanrenxing.service.model.data.User;
import com.sanrenxing.service.model.data.UserRole;
import com.sanrenxing.service.model.data.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository("userPostgreSQL")
public class UserDataAccessService implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addUser(User user) {
        UUID id = UUID.randomUUID();
        String sql = "INSERT into \"users\"(id, name, password, email, status, role, hasProfile)  VALUES(?, ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql,
                id,
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().toString(),
                user.getRole().toString(),
                false
        );
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT * from users;";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
            UserRole role = UserRole.valueOf(resultSet.getString("role"));
            boolean hasProfile = resultSet.getBoolean("hasProfile");
            return new User(id, name, password, email, status, role, hasProfile);
        });
    }

    @Override
    public Optional<User> getUser(UUID id) {
        final String sql = "SELECT * from users WHERE id = ?;";
        try{
            User user = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID userId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
                    UserRole role = UserRole.valueOf(resultSet.getString("role"));
                    boolean hasProfile = resultSet.getBoolean("hasProfile");
                    return new User(userId, name, password, email, status, role, hasProfile);
                });
            return Optional.ofNullable(user);
         } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
    @Override
    public Optional<User> getUserByEmail(String targetEmail) {
        final String sql = "SELECT * from users WHERE email = ?;";
        try{
            User user = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{targetEmail},
                    (resultSet, i) -> {
                        UUID userId = UUID.fromString(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        String password = resultSet.getString("password");
                        String email = resultSet.getString("email");
                        UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
                        UserRole role = UserRole.valueOf(resultSet.getString("role"));
                        boolean hasProfile = resultSet.getBoolean("hasProfile");
                        return new User(userId, name, password, email, status, role, hasProfile);
                    });
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public int deleteUser(UUID id) {
        final String sql = "DELETE FROM users WHERE id = ?;";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateUser(UUID id, User user) {
        // TODO: Conditional updates based on non-null values
        final String sql = """ 
                UPDATE users
                SET name = ?, email = ?, status = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getStatus().toString(),
                id);
    }

    @Override
    public int updateUserProfile(UUID id, boolean hasProfile) {
        final String sql = """ 
                UPDATE users
                SET hasProfile = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql,
                hasProfile,
                id);
    }
}
