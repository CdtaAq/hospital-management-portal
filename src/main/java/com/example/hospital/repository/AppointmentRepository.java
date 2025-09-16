package com.example.hospital.repository;

import com.example.hospital.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndStartTime(Long doctorId, LocalDateTime startTime);

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorIdAndStartTimeBetween(Long doctorId,
                                                        LocalDateTime start,
                                                        LocalDateTime end);

    long countByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
