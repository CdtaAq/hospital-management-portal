package com.hospital.controller;

import com.hospital.model.Treatment;
import com.hospital.service.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
@CrossOrigin(origins = "http://localhost:4200")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) {
        return treatmentService.getTreatmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        return treatmentService.saveTreatment(treatment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(id, treatment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id) {
        return treatmentService.deleteTreatment(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // 🔹 Helper: Get treatments for a given disease
    @GetMapping("/byDisease/{diseaseId}")
    public List<Treatment> getTreatmentsByDisease(@PathVariable Long diseaseId) {
        return treatmentService.getTreatmentsByDisease(diseaseId);
    }

    // 🔹 Helper: Get treatments prescribed to a patient
    @GetMapping("/byPatient/{patientId}")
    public List<Treatment> getTreatmentsByPatient(@PathVariable Long patientId) {
        return treatmentService.getTreatmentsByPatient(patientId);
    }
}
