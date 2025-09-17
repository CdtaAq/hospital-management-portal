import { Component, OnInit } from '@angular/core';
import { Patient } from '../../../core/models/patient.model';
import { PatientService } from '../../../services/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html'
})
export class PatientListComponent implements OnInit {
  patients: Patient[] = [];

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.patientService.getAll().subscribe(p => this.patients = p);
  }
}
