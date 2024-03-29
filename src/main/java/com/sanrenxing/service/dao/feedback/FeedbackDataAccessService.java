package com.sanrenxing.service.dao.feedback;

import com.sanrenxing.service.model.Feedback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("feedbackPostgreSQL")
public class FeedbackDataAccessService implements  FeedbackDao{

    private final JdbcTemplate jdbcTemplate;

    public FeedbackDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addFeedback(Feedback feedback) {
        UUID id = UUID.randomUUID();
        String sql = "INSERT into \"feedback\"(id, fromUser, toUser, rate, comment) VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql,
                id,
                feedback.getFromUser(),
                feedback.getToUser(),
                feedback.getRate(),
                feedback.getComment());
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        final String sql = "SELECT id, fromUser, toUser, rate, comment FROM \"feedback\";";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            UUID fromUser = UUID.fromString(resultSet.getString("fromUser"));
            UUID toUser = UUID.fromString(resultSet.getString("toUser"));
            int rate = resultSet.getInt("rate");
            String comment = resultSet.getString("comment");
            return new Feedback(id, fromUser, toUser, rate, comment);
        });
    }

    @Override
    public Optional<Feedback> getFeedback(UUID id) {
        final String sql = "SELECT id, fromUser, toUser, rate, comment FROM \"feedback\" WHERE id = ?;";
        Feedback feedback = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID feedbackId = UUID.fromString(resultSet.getString("id"));
                    UUID fromUser = UUID.fromString(resultSet.getString("fromUser"));
                    UUID toUser = UUID.fromString(resultSet.getString("toUser"));
                    int rate = resultSet.getInt("rate");
                    String comment = resultSet.getString("comment");
                    return new Feedback(feedbackId, fromUser, toUser, rate, comment);
                }
        );
        return Optional.ofNullable(feedback);

    }

    @Override
    public int deleteFeedback(UUID id) {
        final String sql = "DELETE FROM \"feedback\" WHERE id = ?;";
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public int updateFeedback(UUID id, Feedback feedback) {
        final String sql = """
                UPDATE \"feedback\"
                SET fromUser = ?, toUser = ?, rate = ?, comment = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql,
                feedback.getFromUser(),
                feedback.getToUser(),
                feedback.getRate(),
                feedback.getComment(),
                id
        );
    }
}
