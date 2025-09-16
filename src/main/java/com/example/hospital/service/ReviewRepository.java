package com.example.hospital.repository;

import com.example.hospital.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDoctorId(Long doctorId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.doctor.id = :doctorId")
    double findAverageRatingByDoctor(Long doctorId);
}
