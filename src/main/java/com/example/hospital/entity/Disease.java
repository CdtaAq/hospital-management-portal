package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="disease")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Disease {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String category;
  @Column(length=2000)
  private String description;

  @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL)
  private List<Treatment> treatments;
}
