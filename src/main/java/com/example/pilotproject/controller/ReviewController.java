package com.example.pilotproject.controller;

import com.example.pilotproject.entities.Review;
import com.example.pilotproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/{id}")
    public void postReview(@PathVariable("id") int id,@RequestBody Review review){
        reviewService.postReview(id, review);
    }

    @DeleteMapping("/review/{id}")
    public void deleteReview(@PathVariable("id") int id){
        reviewService.deleteReview(id);
    }
}
