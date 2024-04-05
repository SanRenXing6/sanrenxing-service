package com.sanrenxing.service.dao.token;

import com.sanrenxing.service.common.JsonConverter;
import com.sanrenxing.service.model.data.ConfirmationToken;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
        final UUID userId = confirmationToken.getUser().getId();

        final String sql = """
            INSERT INTO \"token\"(id, token, createdAt, expiresAt, userId)
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
}
