package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.feedback.FeedbackDao;
import com.sanrenxing.service.model.data.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackDao feedbackDao;

    @Autowired
    public FeedbackService(@Qualifier("feedbackMySQL") FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public int addFeedback(Feedback Feedback){
        return feedbackDao.addFeedback(Feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackDao.getAllFeedbacks();
    }

    public Optional<Feedback> getFeedback(UUID id) {
        return feedbackDao.getFeedback(id);
    }

    public int deleteFeedback(UUID id) {
        return feedbackDao.deleteFeedback(id);
    }

    public int updateFeedback(UUID id, Feedback Feedback) {
        return feedbackDao.updateFeedback(id, Feedback);
    }
}
