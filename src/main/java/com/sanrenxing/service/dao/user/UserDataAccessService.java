package com.sanrenxing.service.dao.user;

import com.sanrenxing.service.model.data.User;
import com.sanrenxing.service.model.data.UserRole;
import com.sanrenxing.service.model.data.UserStatus;
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
        String sql = "INSERT into \"user\"(id, name, userName, password, email, status, role, locked, enabled)  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql,
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().toString(),
                user.getRole().toString(),
                user.getLocked(),
                user.getEnabled());
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT * from \"user\";";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.randomUUID();
            String name = resultSet.getString("name");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
            UserRole role = UserRole.valueOf(resultSet.getString("role"));
            Boolean locked = Boolean.valueOf(resultSet.getString("locked"));
            Boolean enabled = Boolean.valueOf(resultSet.getString("enabled"));
            return new User(id, name, userName, password, email, status, role, locked, enabled);
        });
    }

    @Override
    public Optional<User> getUser(UUID id) {
        final String sql = "SELECT * from \"user\" WHERE id = ?;";
        User user = jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            (resultSet, i) -> {
                UUID userId = UUID.randomUUID();
                String name = resultSet.getString("name");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
                UserRole role = UserRole.valueOf(resultSet.getString("role"));
                Boolean locked = Boolean.valueOf(resultSet.getString("locked"));
                Boolean enabled = Boolean.valueOf(resultSet.getString("enabled"));
                return new User(userId, name, userName, password, email, status, role, locked, enabled);
            });
        return Optional.ofNullable(user);
    }
    @Override
    public Optional<User> getUserByEmail(String targetEmail) {
        final String sql = "SELECT * from \"user\" WHERE email = ?;";
        User user = jdbcTemplate.queryForObject(
                sql,
                new Object[]{targetEmail},
                (resultSet, i) -> {
                    UUID userId = UUID.randomUUID();
                    String name = resultSet.getString("name");
                    String userName = resultSet.getString("userName");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
                    UserRole role = UserRole.valueOf(resultSet.getString("role"));
                    Boolean locked = Boolean.valueOf(resultSet.getString("locked"));
                    Boolean enabled = Boolean.valueOf(resultSet.getString("enabled"));
                    return new User(userId, name, userName, password, email, status, role, locked, enabled);
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
