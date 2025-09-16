package com.example.hospital.repository;

import com.example.hospital.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndStartTime(Long doctorId, LocalDateTime startTime);
}
