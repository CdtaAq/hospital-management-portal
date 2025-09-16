package com.example.hospital.controller;

import com.example.hospital.model.Appointment;
import com.example.hospital.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // POST /api/appointments
    @PostMapping
    public Appointment create(@RequestParam Long patientId,
                              @RequestParam Long doctorId,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime) {
        return appointmentService.scheduleAppointment(patientId, doctorId, startTime);
    }

    // PUT /api/appointments/{id}/reschedule
    @PutMapping("/{id}/reschedule")
    public Appointment reschedule(@PathVariable Long id,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newTime) {
        return appointmentService.rescheduleAppointment(id, newTime);
    }

    // GET /api/appointments?patientId=...
    @GetMapping
    public List<Appointment> listForPatient(@RequestParam Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    // GET /api/appointments/doctor/{doctorId}?date=YYYY-MM-DD
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> doctorSchedule(@PathVariable Long doctorId,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAppointmentsForDoctorOnDate(doctorId, date);
    }

    // POST /api/appointments/{id}/confirm
    @PostMapping("/{id}/confirm")
    public Appointment confirm(@PathVariable Long id) {
        return appointmentService.confirmAppointment(id);
    }

    // POST /api/appointments/{id}/emergency
    @PostMapping("/{id}/emergency")
    public Appointment markEmergency(@PathVariable Long id) {
        return appointmentService.markEmergency(id);
    }
}
