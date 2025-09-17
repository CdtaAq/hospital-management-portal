import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppointmentService } from '../../../services/appointment.service';
import { DoctorService } from '../../../services/doctor.service';

@Component({
  selector: 'app-appointment-form',
  templateUrl: './appointment-form.component.html'
})
export class AppointmentFormComponent {
  form: FormGroup;
  doctors = [];

  constructor(private fb: FormBuilder, private doctorSvc: DoctorService, private apptSvc: AppointmentService) {
    this.form = this.fb.group({
      patientId: ['', Validators.required],
      doctorId: ['', Validators.required],
      diseaseId: [''],
      scheduledDate: ['', Validators.required],
      scheduledStart: ['', Validators.required],
      scheduledEnd: ['', Validators.required],
      reason: ['']
    });
    this.doctorSvc.list().subscribe(d => this.doctors = d);
  }

  submit() {
    if (this.form.invalid) return;
    this.apptSvc.create(this.form.value).subscribe({
      next: () => alert('Appointment requested!'),
      error: (err) => alert('Error: ' + err.message)
    });
  }
}
