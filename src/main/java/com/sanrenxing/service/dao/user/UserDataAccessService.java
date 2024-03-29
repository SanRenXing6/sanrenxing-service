package com.sanrenxing.service.dao.user;

import com.sanrenxing.service.model.User;
import com.sanrenxing.service.model.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        String status = UserStatus.OFFLINE.toString();
        String sql = "INSERT into \"user\"(id, name, email, status) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                id,
                user.getName(),
                user.getEmail(),
                status);
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT id, name, email, status from \"user\";";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
            return new User(id, name, email, status);
        });
    }

    @Override
    public Optional<User> getUser(UUID id) {
        final String sql = "SELECT id, name, email, status from \"user\" WHERE id = ?;";
        User user = jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            (resultSet, i) -> {
                UUID userId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
                return new User(userId, name, email, status);
            });
        return Optional.ofNullable(user);

    }

    @Override
    public int deleteUser(UUID id) {
        final String sql = "DELETE FROM \"user\" WHERE id = ?;";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateUser(UUID id, User user) {
        // TODO: Conditional updates based on non-null values
        final String sql = """ 
                UPDATE \"user\"
                SET name = ?, email = ?, status = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getStatus().toString(),
                id);
    }
}
