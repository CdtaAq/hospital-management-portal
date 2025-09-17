import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../../../services/doctor.service';
import { Doctor } from '../../../models/doctor';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html'
})
export class DoctorListComponent implements OnInit {
  doctors: Doctor[] = [];
  specialty = '';

  constructor(private svc: DoctorService) {}

  ngOnInit() { this.load(); }

  load() {
    this.svc.list(this.specialty || undefined).subscribe(d => this.doctors = d);
  }

  search() { this.load(); }
}
