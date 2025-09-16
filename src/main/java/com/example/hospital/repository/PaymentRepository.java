package com.example.hospital.repository;

import com.example.hospital.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPatientId(Long patientId);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.status = 'PAID'")
    double sumPaidAmount();

    @Query("SELECT AVG(p.amount) FROM Payment p WHERE p.status = 'PAID'")
    double avgPaidAmount();
}
