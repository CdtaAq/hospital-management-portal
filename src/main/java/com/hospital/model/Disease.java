package com.hospital.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "diseases")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category; // e.g., cardiology, urology
    @Column(length = 2000)
    private String description;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL)
    private List<Treatment> treatments;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }

    public List<Treatment> getTreatments() { return treatments; }
    public void setTreatments(List<Treatment> treatments) { this.treatments = treatments; }
}
