package com.hospital.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // ROLE_ADMIN, ROLE_DOCTOR, ROLE_PATIENT
}
