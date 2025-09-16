package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="appointment")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Appointment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="patient_id", nullable=false)
  private Patient patient;

  @ManyToOne
  @JoinColumn(name="doctor_id", nullable=false)
  private Doctor doctor;

  @ManyToOne
  @JoinColumn(name="disease_id")
  private Disease disease;

  private LocalDate scheduledDate;
  private LocalTime scheduledStart;
  private LocalTime scheduledEnd;

  private String status; // SCHEDULED, CONFIRMED, CANCELLED, etc
  @Column(length=2000)
  private String reason;
}
