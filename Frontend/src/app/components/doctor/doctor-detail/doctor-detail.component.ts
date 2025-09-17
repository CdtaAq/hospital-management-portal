import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DoctorService } from '../../../services/doctor.service';
import { Doctor } from '../../../core/models/doctor.model';

@Component({
  selector: 'app-doctor-detail',
  templateUrl: './doctor-detail.component.html'
})
export class DoctorDetailComponent implements OnInit {
  doctor?: Doctor;
  id?: number;

  constructor(private route: ActivatedRoute, private doctorService: DoctorService, private router: Router) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.doctorService.get(this.id).subscribe(d => this.doctor = d);
    }
  }

  goEdit() {
    if (this.id) this.router.navigate(['/doctors/new'], { queryParams: { editId: this.id } });
  }
}
