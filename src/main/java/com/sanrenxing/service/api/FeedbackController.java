package com.sanrenxing.service.api;


import com.sanrenxing.service.model.Feedback;
import com.sanrenxing.service.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/feedbacks")
@RestController
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public void addFeedback(@Valid @RequestBody @NonNull Feedback feedback) {
        feedbackService.addFeedback(feedback);
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping(path="{id}")
    public Feedback getFeedbackById(@PathVariable("id") UUID id) {
        return feedbackService.getFeedback(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deleteFeedback(@PathVariable("id") UUID id) {
        feedbackService.deleteFeedback(id);
    }

    @PutMapping(path="{id}")
    public void updateFeedback(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Feedback feedback) {
        feedbackService.updateFeedback(id, feedback);
    }

}
