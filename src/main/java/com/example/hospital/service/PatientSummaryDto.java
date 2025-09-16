package com.example.hospital.dto;

import com.example.hospital.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PatientSummaryDto {
    private Patient patient;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;
    private List<Admission> admissions;
    private List<Payment> payments;
}
