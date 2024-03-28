package com.sanrenxing.service.dao.feedback;

import com.sanrenxing.service.model.Feedback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FeedbackDataAccessService implements  FeedbackDao{

    private final JdbcTemplate jdbcTemplate;

    public FeedbackDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addFeedback(Feedback feedback) {
        UUID id = UUID.randomUUID();
        String sql = "INSERT into \"\"(id, fromUser toUser, rate, comment) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                id,
                feedback.getFromUser(),
                feedback.getToUser(),
                feedback.getRate(),
                feedback.getComment());
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return null;
    }

    @Override
    public Optional<Feedback> getFeedback(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteFeedback(UUID id) {
        return 0;
    }

    @Override
    public int updateFeedback(UUID id, Feedback feedback) {
        return 0;
    }
}
