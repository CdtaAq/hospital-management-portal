package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="payment")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="appointment_id")
  private Appointment appointment;

  private Double amount;
  private String method;
  private String status; // PENDING, PAID
  private String transactionRef;
  private OffsetDateTime paidAt;
}
