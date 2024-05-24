package com.sanrenxing.service.dao.message;

import com.sanrenxing.service.model.data.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository("messagePostgreSQL")
public class MessageDataAccessService implements MessageDao{
    private final JdbcTemplate jdbcTemplate;

    public MessageDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int addMessage(Message message) {
        UUID id = UUID.randomUUID();
        String sql = "INSERT into messages(id, from_user, to_user, content) VALUES (?, ?, ?, ?);";
        return jdbcTemplate.update(sql,
                id,
                message.getFromUser(),
                message.getToUser(),
                message.getContent());
    }

    @Override
    public List<Message> getMessagesToUser(UUID userId) {
        final String sql = "SELECT id, from_user, to_user, content, created_at FROM messages WHERE from_user=? OR to_user=? ORDER BY created_at;";
        return jdbcTemplate.query(sql,
                new Object[]{userId, userId},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    UUID from_user = UUID.fromString(resultSet.getString("from_user"));
                    UUID to_user = UUID.fromString(resultSet.getString("to_user"));
                    String content = resultSet.getString("content");
                    LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                    return new Message(id, from_user, to_user, content, created_at);
                });
    }

    @Override
    public int deleteAllMessage(UUID userId) {
        final String sql = "DELETE FROM messages WHERE from_user = ? OR to_user = ?;";
        return jdbcTemplate.update(sql, userId, userId);
    }
}
