package com.hospital.service;

import com.hospital.model.Disease;
import com.hospital.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    public Optional<Disease> getDiseaseById(Long id) {
        return diseaseRepository.findById(id);
    }

    public Disease saveDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    public Optional<Disease> updateDisease(Long id, Disease diseaseDetails) {
        return diseaseRepository.findById(id).map(disease -> {
            disease.setName(diseaseDetails.getName());
            disease.setCategory(diseaseDetails.getCategory());
            disease.setDescription(diseaseDetails.getDescription());
            return diseaseRepository.save(disease);
        });
    }

    public boolean deleteDisease(Long id) {
        return diseaseRepository.findById(id).map(disease -> {
            diseaseRepository.delete(disease);
            return true;
        }).orElse(false);
    }

    public List<Disease> getDiseasesByCategory(String category) {
        return diseaseRepository.findByCategoryIgnoreCase(category);
    }
}
