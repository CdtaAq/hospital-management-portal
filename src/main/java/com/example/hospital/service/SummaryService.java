package com.example.hospital.service;

import com.example.hospital.dto.PatientSummaryDto;
import com.example.hospital.dto.StatisticsDto;
import com.example.hospital.model.*;
import com.example.hospital.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final AdmissionRepository admissionRepository;
    private final PaymentRepository paymentRepository;
    private final PatientRepository patientRepository;

    // Patient summary
    public PatientSummaryDto getPatientSummary(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
        List<Admission> admissions = admissionRepository.findByPatientId(patientId);
        List<Payment> payments = paymentRepository.findByPatientId(patientId);

        return new PatientSummaryDto(patient, appointments, prescriptions, admissions, payments);
    }

    // Admin statistics
    public StatisticsDto getStatistics() {
        long totalPatients = patientRepository.count();

        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();

        long appointmentsThisMonth = appointmentRepository.countByStartTimeBetween(start.atStartOfDay(), end.atTime(23,59));

        double revenue = paymentRepository.sumPaidAmount();
        double avgTreatmentCost = paymentRepository.avgPaidAmount();

        return new StatisticsDto(totalPatients, appointmentsThisMonth, revenue, avgTreatmentCost);
    }
}
