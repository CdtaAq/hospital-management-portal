package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticsDto {
    private long totalPatients;
    private long appointmentsThisMonth;
    private double totalRevenue;
    private double avgTreatmentCost;
}
