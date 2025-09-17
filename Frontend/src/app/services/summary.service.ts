import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Summary } from '../core/models/summary.model';

@Injectable({ providedIn: 'root' })
export class SummaryService {
  private readonly API_BASE = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) {}

  // GET /api/admin/patients/{patientId}/summary
  getPatientSummary(patientId: number): Observable<Summary> {
    return this.http.get<Summary>(`${this.API_BASE}/patients/${patientId}/summary`);
  }

  // GET /api/admin/statistics
  getStatistics(): Observable<any> {
    return this.http.get<any>(`${this.API_BASE}/statistics`);
  }
}
