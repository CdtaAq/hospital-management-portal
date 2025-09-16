package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="review")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Review {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Doctor doctor;

  @ManyToOne
  private Patient patient;

  private Integer rating;
  @Column(length=2000)
  private String comment;
  private OffsetDateTime createdAt;
}
