package com.example.hospital.service;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Payment;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AppointmentRepository appointmentRepository;

    // Create dummy payment record
    public Payment createPayment(Long appointmentId, Double amount) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Payment payment = Payment.builder()
                .appointment(appointment)
                .amount(amount)
                .status("PENDING")
                .paymentDate(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }

    // Mark payment as paid (admin action)
    public Payment markAsPaid(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus("PAID");
        return paymentRepository.save(payment);
    }
}
