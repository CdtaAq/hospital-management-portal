import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payment } from '../core/models/payment.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PaymentService {
  private readonly API = `${environment.apiUrl}/payments`;
  constructor(private http: HttpClient) {}

  create(payload: Partial<Payment>): Observable<Payment> {
    return this.http.post<Payment>(this.API, payload);
  }

  markPaid(id: number): Observable<Payment> {
    return this.http.put<Payment>(`${this.API}/${id}/mark-paid`, {});
  }

  get(id: number) { return this.http.get<Payment>(`${this.API}/${id}`); }

  findByPatient(patientId: number) {
    return this.http.get<Payment[]>(`${this.API}?patientId=${patientId}`);
  }

  delete(id: number) { return this.http.delete<void>(`${this.API}/${id}`); }
}
