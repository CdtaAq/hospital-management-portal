package com.example.hospital.controller;

import com.example.hospital.model.Payment;
import com.example.hospital.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // POST /api/payments
    @PostMapping
    public Payment createPayment(@RequestParam Long appointmentId,
                                 @RequestParam Double amount) {
        return paymentService.createPayment(appointmentId, amount);
    }

    // PUT /api/payments/{id}/mark-paid
    @PutMapping("/{id}/mark-paid")
    public Payment markPaid(@PathVariable Long id) {
        return paymentService.markAsPaid(id);
    }
}
