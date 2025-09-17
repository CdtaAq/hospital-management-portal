import { Component, OnInit } from '@angular/core';
import { Doctor } from '../../../core/models/doctor.model';
import { DoctorService } from '../../../services/doctor.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html'
})
export class DoctorFormComponent implements OnInit {
  model: Doctor = { name: '', specialty: '', experience: 0, fees: 0 };
  editId?: number;
  message = '';

  constructor(private svc: DoctorService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const q = this.route.snapshot.queryParamMap.get('editId');
    if (q) {
      this.editId = Number(q);
      this.svc.get(this.editId).subscribe(d => this.model = d);
    }
  }

  save() {
    if (this.editId) {
      this.svc.update(this.editId, this.model).subscribe(() => {
        this.message = 'Doctor updated';
        this.router.navigate(['/doctors']);
      });
    } else {
      this.svc.create(this.model).subscribe(() => {
        this.message = 'Doctor created';
        this.router.navigate(['/doctors']);
      });
    }
  }
}
