package com.example.hospital.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "admissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor admittingDoctor;

    @Column(nullable = false)
    private LocalDate admissionDate;

    private LocalDate dischargeDate;

    private String roomNumber;

    private String reason;

    private String status; // ADMITTED, DISCHARGED, ICU, etc.
}
