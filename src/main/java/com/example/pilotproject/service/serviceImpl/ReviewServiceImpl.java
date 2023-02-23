package com.example.pilotproject.service.serviceImpl;

import com.example.pilotproject.entities.Hospital;
import com.example.pilotproject.entities.Review;
import com.example.pilotproject.repository.HospitalRepository;
import com.example.pilotproject.repository.ReviewRepository;
import com.example.pilotproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public void postReview(int id, Review review) {
        boolean exists = hospitalRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't post review for hospital with id: " + id);
        }else{
            Hospital hospital = hospitalRepository.findById(id).get();
            review.setHospital(hospital);
            reviewRepository.save(review);
        }
    }

    @Override
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }

}
