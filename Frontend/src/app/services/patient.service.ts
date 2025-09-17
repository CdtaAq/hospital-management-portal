import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../core/models/patient.model';

@Injectable({ providedIn: 'root' })
export class PatientService {
  private readonly API_BASE = 'http://localhost:8080/api/patients';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.API_BASE);
  }

  get(id: number): Observable<Patient> {
    return this.http.get<Patient>(`${this.API_BASE}/${id}`);
  }

  create(payload: Patient): Observable<Patient> {
    return this.http.post<Patient>(this.API_BASE, payload);
  }

  update(id: number, payload: Patient): Observable<Patient> {
    return this.http.put<Patient>(`${this.API_BASE}/${id}`, payload);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_BASE}/${id}`);
  }

  // helper: search by disease id (if backend supports)
  findByDisease(diseaseId: number): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.API_BASE}?diseaseId=${diseaseId}`);
  }
}
