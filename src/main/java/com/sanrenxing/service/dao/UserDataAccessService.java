package com.sanrenxing.service.dao;

import com.sanrenxing.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addUser(User user) {
        UUID id = UUID.randomUUID();
        String sql = "INSERT into \"user\"(id, name, email) VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql,
                id,
                user.getName(),
                user.getEmail());
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT id, name, email from \"user\";";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            return new User(id, name, email);
        });
    }

    @Override
    public Optional<User> getUser(UUID id) {
        final String sql = "SELECT id, name, email from \"user\" WHERE id = ?;";
        User user = jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            (resultSet, i) -> {
                UUID userId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                return new User(userId, name, email);
            });
        return Optional.ofNullable(user);

    }

    @Override
    public int deleteUser(UUID id) {
        String sql = "DELETE FROM \"user\" WHERE id = ?;";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateUser(UUID id, User user) {
        String sql = """ 
                UPDATE \"user\"
                SET name = ?, email = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                id);
    }
}
