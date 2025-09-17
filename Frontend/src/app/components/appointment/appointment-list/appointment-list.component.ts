import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../../core/models/appointment.model';
import { AppointmentService } from '../../../services/appointment.service';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html'
})
export class AppointmentListComponent implements OnInit {
  appointments: Appointment[] = [];

  constructor(private appointmentService: AppointmentService) {}

  ngOnInit(): void {
    // Example: patientId = 1 for demo
    this.appointmentService.byPatient(1).subscribe(a => this.appointments = a);
  }
}
