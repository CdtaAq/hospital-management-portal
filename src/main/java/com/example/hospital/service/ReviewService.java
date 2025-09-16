package com.example.hospital.service;

import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.model.Review;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    // Create review + recompute doctor rating
    public Review createReview(Long patientId, Long doctorId, int rating, String comment) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Review review = Review.builder()
                .patient(patient)
                .doctor(doctor)
                .rating(rating)
                .comment(comment)
                .build();

        Review saved = reviewRepository.save(review);

        // Update doctor rating (average of all reviews)
        double avgRating = reviewRepository.findAverageRatingByDoctor(doctorId);
        doctor.setAverageRating(avgRating);
        doctorRepository.save(doctor);

        return saved;
    }

    public List<Review> getReviewsForDoctor(Long doctorId) {
        return reviewRepository.findByDoctorId(doctorId);
    }
}
