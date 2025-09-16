package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="patient")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private LocalDate dob;
  private String gender;
  private String phone;
  private String email;
  @Column(length=2000)
  private String address;
  @Column(length=4000)
  private String medicalHistory;
}
