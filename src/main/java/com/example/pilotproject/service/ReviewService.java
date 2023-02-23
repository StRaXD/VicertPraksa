package com.example.pilotproject.service;

import com.example.pilotproject.entities.Review;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    void postReview(int id, Review review);

    void deleteReview(int id);
}
