package com.example.hospital.service;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.DoctorAvailability;
import com.example.hospital.model.Patient;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.DoctorAvailabilityRepository;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorAvailabilityRepository availabilityRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    // Create and schedule appointment
    @Transactional
    public Appointment scheduleAppointment(Long patientId, Long doctorId, LocalDateTime startTime) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // 1. Check doctor's availability
        List<DoctorAvailability> availabilities = availabilityRepository.findByDoctorId(doctorId);

        boolean isAvailable = availabilities.stream().anyMatch(av -> {
            boolean sameDay = av.getDayOfWeek().equals(startTime.getDayOfWeek());
            boolean withinTime = !startTime.toLocalTime().isBefore(av.getStartTime())
                    && !startTime.toLocalTime().isAfter(av.getEndTime());
            return sameDay && withinTime && av.getAvailable();
        });

        if (!isAvailable) {
            throw new RuntimeException("Doctor not available at this time");
        }

        // 2. Check for conflicts with existing appointments
        boolean conflict = appointmentRepository.existsByDoctorIdAndStartTime(doctorId, startTime);
        if (conflict) {
            throw new RuntimeException("Appointment slot already booked");
        }

        // 3. Create appointment
        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .startTime(startTime)
                .status("BOOKED")
                .build();

        Appointment saved = appointmentRepository.save(appointment);

        // 4. Send notifications (dummy log for now)
        System.out.println("Notification: Appointment booked for patient "
                + patient.getName() + " with Dr. " + doctor.getName() + " at " + startTime);

        return saved;
    }

    // Reschedule appointment
    @Transactional
    public Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newTime) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus("RESCHEDULED");
        appointment.setStartTime(newTime);

        return appointmentRepository.save(appointment);
    }

    // Cancel appointment
    @Transactional
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);

        System.out.println("Notification: Appointment cancelled for patient "
                + appointment.getPatient().getName());
    }

    // --- 🔑 Additional helper methods ---

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsForDoctorOnDate(Long doctorId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59);
        return appointmentRepository.findByDoctorIdAndStartTimeBetween(doctorId, start, end);
    }

    @Transactional
    public Appointment confirmAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus("CONFIRMED");
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment markEmergency(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus("EMERGENCY");
        return appointmentRepository.save(appointment);
    }
}
