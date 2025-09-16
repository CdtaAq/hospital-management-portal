package com.example.hospital.controller;

import com.example.hospital.dto.PatientSummaryDto;
import com.example.hospital.dto.StatisticsDto;
import com.example.hospital.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    // GET /api/admin/patients/{patientId}/summary
    @GetMapping("/patients/{patientId}/summary")
    public PatientSummaryDto getPatientSummary(@PathVariable Long patientId) {
        return summaryService.getPatientSummary(patientId);
    }

    // GET /api/admin/statistics
    @GetMapping("/statistics")
    public StatisticsDto getStatistics() {
        return summaryService.getStatistics();
    }
}
