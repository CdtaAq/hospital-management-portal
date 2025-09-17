import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Summary } from '../core/models/summary.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class SummaryService {
  private readonly API = `${environment.apiUrl}/admin`;
  constructor(private http: HttpClient) {}

  getPatientSummary(patientId: number): Observable<Summary> {
    return this.http.get<Summary>(`${this.API}/patients/${patientId}/summary`);
  }

  getStatistics(): Observable<any> {
    return this.http.get<any>(`${this.API}/statistics`);
  }
}
