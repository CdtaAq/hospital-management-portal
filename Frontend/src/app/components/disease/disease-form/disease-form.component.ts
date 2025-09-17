import { Component, OnInit } from '@angular/core';
import { Disease } from '../../../core/models/disease.model';
import { DiseaseService } from '../../../services/disease.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-disease-form',
  templateUrl: './disease-form.component.html'
})
export class DiseaseFormComponent implements OnInit {
  model: Disease = { name: '', description: '' };
  message = '';
  editId?: number;

  constructor(private svc: DiseaseService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const edit = this.route.snapshot.queryParamMap.get('editId');
    if (edit) {
      this.editId = Number(edit);
      this.svc.get(this.editId).subscribe(d => this.model = d);
    }
  }

  save() {
    if (this.editId) {
      this.svc.update(this.editId, this.model).subscribe(() => { this.message = 'Updated'; this.router.navigate(['/diseases']); });
    } else {
      this.svc.create(this.model).subscribe(() => { this.message = 'Created'; this.router.navigate(['/diseases']); });
    }
  }
}
