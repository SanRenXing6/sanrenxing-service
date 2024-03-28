package com.sanrenxing.service.dao.feedback;

import com.sanrenxing.service.model.Feedback;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeedbackDao {
    int addFeedback(Feedback feedback);

    List<Feedback> getAllFeedbacks();

    Optional<Feedback> getFeedback(UUID id);

    int deleteFeedback(UUID id);

    int updateFeedback(UUID id, Feedback feedback);
}
