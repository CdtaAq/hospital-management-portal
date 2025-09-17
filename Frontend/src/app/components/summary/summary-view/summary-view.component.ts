import { Component, OnInit } from '@angular/core';
import { SummaryService } from '../../../services/summary.service';
import { Summary } from '../../../core/models/summary.model';

@Component({
  selector: 'app-summary-view',
  templateUrl: './summary-view.component.html'
})
export class SummaryViewComponent implements OnInit {
  summary?: Summary;

  constructor(private summaryService: SummaryService) {}

  ngOnInit(): void {
    // Demo: load patientId=1
    this.summaryService.getPatientSummary(1).subscribe(s => this.summary = s);
  }
}
