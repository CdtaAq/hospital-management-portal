package com.hospital.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${stripe.publishable-key}")
    private String publishableKey;

    // Create payment intent
    @PostMapping("/create-payment-intent")
    public Map<String, Object> createPaymentIntent(@RequestBody Map<String, Object> request) throws StripeException {
        Long amount = Long.valueOf(request.get("amount").toString()); // amount in cents
        String currency = request.get("currency").toString();

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", currency);
        params.put("automatic_payment_methods", Map.of("enabled", true));

        PaymentIntent intent = PaymentIntent.create(params);

        Map<String, Object> response = new HashMap<>();
        response.put("clientSecret", intent.getClientSecret());
        response.put("publishableKey", publishableKey);

        return response;
    }
}
