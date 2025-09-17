import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../core/models/patient.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PatientService {
  private readonly API = `${environment.apiUrl}/patients`;
  constructor(private http: HttpClient) {}

  getAll(): Observable<Patient[]> { return this.http.get<Patient[]>(this.API); }
  get(id: number): Observable<Patient> { return this.http.get<Patient>(`${this.API}/${id}`); }
  create(payload: Patient): Observable<Patient> { return this.http.post<Patient>(this.API, payload); }
  update(id: number, payload: Patient): Observable<Patient> { return this.http.put<Patient>(`${this.API}/${id}`, payload); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.API}/${id}`); }
  byDisease(diseaseId: number): Observable<Patient[]> { return this.http.get<Patient[]>(`${this.API}/byDisease/${diseaseId}`); }
}
