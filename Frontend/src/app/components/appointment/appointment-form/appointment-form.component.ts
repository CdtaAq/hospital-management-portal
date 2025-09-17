import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../../core/models/appointment.model';
import { AppointmentService } from '../../../services/appointment.service';
import { DoctorService } from '../../../services/doctor.service';
import { PatientService } from '../../../services/patient.service';
import { Doctor } from '../../../core/models/doctor.model';
import { Patient } from '../../../core/models/patient.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appointment-form',
  templateUrl: './appointment-form.component.html'
})
export class AppointmentFormComponent implements OnInit {
  model: Partial<Appointment> = { patientId: 0, doctorId: 0, startTime: '' };
  doctors: Doctor[] = [];
  patients: Patient[] = [];
  message = '';

  constructor(
    private svc: AppointmentService,
    private doctorSvc: DoctorService,
    private patientSvc: PatientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.doctorSvc.getAll().subscribe(d => this.doctors = d);
    this.patientSvc.getAll().subscribe(p => this.patients = p);
  }

  submit() {
    if (!this.model.patientId || !this.model.doctorId || !this.model.startTime) {
      this.message = 'Please fill required fields';
      return;
    }
    this.svc.schedule(this.model).subscribe({
      next: () => { this.message = 'Appointment scheduled'; this.router.navigate(['/appointments']); },
      error: (err) => { this.message = 'Error: ' + (err?.error?.message || err.message); }
    });
  }
}
