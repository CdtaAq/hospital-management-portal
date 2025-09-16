package com.example.hospital.service;

import com.example.hospital.entity.Doctor;
import com.example.hospital.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository repo;
    public DoctorService(DoctorRepository repo) { this.repo = repo; }

    public Doctor create(Doctor d) { return repo.save(d); }
    public Optional<Doctor> findById(Long id) { return repo.findById(id); }
    public List<Doctor> findAll() { return repo.findAll(); }
    public List<Doctor> findBySpecialty(String specialty) { return repo.findBySpecialtyContainingIgnoreCase(specialty); }
    public Doctor update(Doctor d) { return repo.save(d); }
    public void delete(Long id) { repo.deleteById(id); }
}
