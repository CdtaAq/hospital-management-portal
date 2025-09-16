package com.example.hospital.controller;

import com.example.hospital.model.Review;
import com.example.hospital.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // POST /api/reviews
    @PostMapping("/api/reviews")
    public Review create(@RequestParam Long patientId,
                         @RequestParam Long doctorId,
                         @RequestParam int rating,
                         @RequestParam String comment) {
        return reviewService.createReview(patientId, doctorId, rating, comment);
    }

    // GET /api/doctors/{id}/reviews
    @GetMapping("/api/doctors/{id}/reviews")
    public List<Review> listForDoctor(@PathVariable Long id) {
        return reviewService.getReviewsForDoctor(id);
    }
}
