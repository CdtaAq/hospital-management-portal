package com.hospital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 2000)
    private String description;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public Disease getDisease() { return disease; }
    public void setDisease(Disease disease) { this.disease = disease; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
