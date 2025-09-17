import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payment } from '../core/models/payment.model';

@Injectable({ providedIn: 'root' })
export class PaymentService {
  private readonly API_BASE = 'http://localhost:8080/api/payments';

  constructor(private http: HttpClient) {}

  // create dummy payment record
  create(payload: Partial<Payment>): Observable<Payment> {
    // backend expects appointmentId & amount as request params or body; using body
    return this.http.post<Payment>(this.API_BASE, payload);
  }

  get(id: number): Observable<Payment> {
    return this.http.get<Payment>(`${this.API_BASE}/${id}`);
  }

  // admin action to mark paid
  markPaid(id: number): Observable<Payment> {
    return this.http.put<Payment>(`${this.API_BASE}/${id}/mark-paid`, {});
  }

  // list payments by patient (backend: GET /api/payments?patientId=)
  findByPatient(patientId: number) {
    return this.http.get<Payment[]>(`${this.API_BASE}?patientId=${patientId}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_BASE}/${id}`);
  }
}
