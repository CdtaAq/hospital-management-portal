import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment } from '../core/models/appointment.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AppointmentService {
  private readonly API = `${environment.apiUrl}/appointments`;
  constructor(private http: HttpClient) {}

  // schedule expects body { patientId, doctorId, startTime }
  schedule(payload: Partial<Appointment>): Observable<Appointment> {
    return this.http.post<Appointment>(this.API, payload);
  }

  reschedule(id: number, newTime: string): Observable<Appointment> {
    const params = new HttpParams().set('newTime', newTime);
    return this.http.put<Appointment>(`${this.API}/${id}/reschedule`, {}, { params });
  }

  cancel(id: number): Observable<void> { return this.http.delete<void>(`${this.API}/${id}`); }

  byPatient(patientId: number): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.API}?patientId=${patientId}`);
  }

  forDoctorOnDate(doctorId: number, date: string): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.API}/doctor/${doctorId}?date=${date}`);
  }

  confirm(id: number): Observable<Appointment> { return this.http.post<Appointment>(`${this.API}/${id}/confirm`, {}); }

  markEmergency(id: number): Observable<Appointment> { return this.http.post<Appointment>(`${this.API}/${id}/emergency`, {}); }
}
