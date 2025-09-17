package com.hospital.controller;

import com.hospital.model.Disease;
import com.hospital.service.DiseaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diseases")
@CrossOrigin(origins = "http://localhost:4200")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public List<Disease> getAllDiseases() {
        return diseaseService.getAllDiseases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disease> getDiseaseById(@PathVariable Long id) {
        return diseaseService.getDiseaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Disease createDisease(@RequestBody Disease disease) {
        return diseaseService.saveDisease(disease);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disease> updateDisease(@PathVariable Long id, @RequestBody Disease disease) {
        return diseaseService.updateDisease(id, disease)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisease(@PathVariable Long id) {
        return diseaseService.deleteDisease(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // 🔹 Helper: Get diseases by category (like "cardio", "neuro")
    @GetMapping("/category/{category}")
    public List<Disease> getDiseasesByCategory(@PathVariable String category) {
        return diseaseService.getDiseasesByCategory(category);
    }
}
