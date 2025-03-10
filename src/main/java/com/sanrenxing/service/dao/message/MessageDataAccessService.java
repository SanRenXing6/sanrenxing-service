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
        String sql = "INSERT into messages(id, from_user_id, from_user_name, to_user_id, to_user_name, content) VALUES (?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql,
                id,
                message.getFromUserId(),
                message.getFromUserName(),
                message.getToUserId(),
                message.getToUserName(),
                message.getContent());
    }

    @Override
    public List<Message> getMessagesToUser(UUID userId) {
        final String sql = "SELECT id, from_user_id, from_user_name, to_user_id, to_user_name, content, created_at FROM messages WHERE from_user_id=? OR to_user_id=? ORDER BY created_at;";
        return jdbcTemplate.query(sql,
                new Object[]{userId, userId},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    UUID from_user_id = UUID.fromString(resultSet.getString("from_user_id"));
                    String from_user_name = resultSet.getString("from_user_name");
                    UUID to_user_id = UUID.fromString(resultSet.getString("to_user_id"));
                    String to_user_name = resultSet.getString("to_user_name");
                    String content = resultSet.getString("content");
                    LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                    return new Message(id, from_user_id, from_user_name, to_user_id, to_user_name, content, created_at);
                });
    }

    @Override
    public int deleteAllMessage(UUID userId) {
        final String sql = "DELETE FROM messages WHERE from_user_id = ? OR to_user = ?;";
        return jdbcTemplate.update(sql, userId, userId);
    }
}
