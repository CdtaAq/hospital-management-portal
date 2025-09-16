package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="doctor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private Integer experienceYears;
    private Double fees;
    private String phone;
    private String email;
    @Column(length=2000)
    private String bio;
    private Double rating;

    @ManyToMany
    @JoinTable(name="doctor_disease",
       joinColumns = @JoinColumn(name="doctor_id"),
       inverseJoinColumns = @JoinColumn(name="disease_id"))
    private Set<Disease> diseases;
}
