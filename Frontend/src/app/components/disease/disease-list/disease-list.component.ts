import { Component, OnInit } from '@angular/core';
import { Disease } from '../../../core/models/disease.model';
import { DiseaseService } from '../../../services/disease.service';

@Component({
  selector: 'app-disease-list',
  templateUrl: './disease-list.component.html'
})
export class DiseaseListComponent implements OnInit {
  diseases: Disease[] = [];

  constructor(private diseaseService: DiseaseService) {}

  ngOnInit(): void {
    this.diseaseService.getAll().subscribe(d => this.diseases = d);
  }
}
