package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="treatment")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Treatment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="disease_id")
  private Disease disease;

  private String name;
  private Integer estimatedTimeDays;
  private Double estimatedCost;
  @Column(length=2000)
  private String description;
}
