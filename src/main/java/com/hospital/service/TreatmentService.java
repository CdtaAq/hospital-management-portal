package com.hospital.service;

import com.hospital.model.Treatment;
import com.hospital.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> getTreatmentById(Long id) {
        return treatmentRepository.findById(id);
    }

    public Treatment saveTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Optional<Treatment> updateTreatment(Long id, Treatment treatmentDetails) {
        return treatmentRepository.findById(id).map(treatment -> {
            treatment.setName(treatmentDetails.getName());
            treatment.setDescription(treatmentDetails.getDescription());
            treatment.setCost(treatmentDetails.getCost());
            treatment.setDisease(treatmentDetails.getDisease());
            treatment.setPatient(treatmentDetails.getPatient());
            return treatmentRepository.save(treatment);
        });
    }

    public boolean deleteTreatment(Long id) {
        return treatmentRepository.findById(id).map(treatment -> {
            treatmentRepository.delete(treatment);
            return true;
        }).orElse(false);
    }

    public List<Treatment> getTreatmentsByDisease(Long diseaseId) {
        return treatmentRepository.findByDiseaseId(diseaseId);
    }

    public List<Treatment> getTreatmentsByPatient(Long patientId) {
        return treatmentRepository.findByPatientId(patientId);
    }
}
