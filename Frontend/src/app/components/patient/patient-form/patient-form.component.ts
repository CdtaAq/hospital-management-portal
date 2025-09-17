import { Component, OnInit } from '@angular/core';
import { Patient } from '../../../core/models/patient.model';
import { PatientService } from '../../../services/patient.service';
import { DiseaseService } from '../../../services/disease.service';
import { Disease } from '../../../core/models/disease.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html'
})
export class PatientFormComponent implements OnInit {
  model: Patient = { name: '', age: 0, gender: '' };
  diseases: Disease[] = [];
  editId?: number;
  message = '';

  constructor(
    private svc: PatientService,
    private diseaseSvc: DiseaseService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.diseaseSvc.getAll().subscribe(d => this.diseases = d);
    const edit = this.route.snapshot.queryParamMap.get('editId');
    if (edit) {
      this.editId = Number(edit);
      this.svc.get(this.editId).subscribe(p => this.model = p);
    }
  }

  save() {
    if (this.editId) {
      this.svc.update(this.editId, this.model).subscribe(() => { this.message = 'Updated'; this.router.navigate(['/patients']); });
    } else {
      this.svc.create(this.model).subscribe(() => { this.message = 'Created'; this.router.navigate(['/patients']); });
    }
  }
}
