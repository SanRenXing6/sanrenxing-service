package com.sanrenxing.service.dao.token;

import com.sanrenxing.service.model.data.ConfirmationToken;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository("tokenPostgreSQL")
public class TokenDataAccessService implements TokenDao {

    private final JdbcTemplate jdbcTemplate;

    public TokenDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addToken(ConfirmationToken confirmationToken) {
        final UUID id = UUID.randomUUID();
        final String token = confirmationToken.getToken();
        final Timestamp createdAt = Timestamp.valueOf(confirmationToken.getCreatedAt());
        final Timestamp expiresAt = Timestamp.valueOf(confirmationToken.getExpiresAt());
        final UUID userId = confirmationToken.getUserId();

        final String sql = """
            INSERT INTO tokens(id, token, createdAt, expiresAt, userId)
            VALUES(?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                id,
                token,
                createdAt,
                expiresAt,
                userId
        );
    }

    @Override
    public Optional<ConfirmationToken> getToken(String targetToken) {
        final String sql = "SELECT * FROM tokens WHERE token = ?;";
        ConfirmationToken confirmationToken;
        try {
            confirmationToken = jdbcTemplate.queryForObject(sql,
                    new Object[]{targetToken},
                    (resultSet, i) -> {
                        LocalDateTime confirmedAt = null;
                        UUID id = UUID.fromString(resultSet.getString("id"));
                        String token = resultSet.getString("token");
                        LocalDateTime createdAt = resultSet.getTimestamp("createdAt").toLocalDateTime();
                        LocalDateTime expiresAt = resultSet.getTimestamp("expiresAt").toLocalDateTime();
                        Timestamp confirmedAtTimeStamp = resultSet.getTimestamp("confirmedAt");
                        if(confirmedAtTimeStamp!=null){
                            confirmedAt = confirmedAtTimeStamp.toLocalDateTime();
                        }
                        UUID userId = UUID.fromString(resultSet.getString("userId"));
                        return new ConfirmationToken(id, token, createdAt, expiresAt, confirmedAt, userId);
                    });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(confirmationToken);
    }

    @Override
    public void setConfirmedAt(String token) {
        final Timestamp confirmedAt = Timestamp.valueOf(LocalDateTime.now());
        final String sql = """
                UPDATE tokens
                SET confirmedAt = ?
                WHERE token = ?
                """;
        jdbcTemplate.update(sql,
                confirmedAt,
                token);

    }
}
