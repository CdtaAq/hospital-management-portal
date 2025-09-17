import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../core/models/review.model';

@Injectable({ providedIn: 'root' })
export class ReviewService {
  private readonly API_BASE = 'http://localhost:8080/api/reviews';

  constructor(private http: HttpClient) {}

  create(payload: Partial<Review>): Observable<Review> {
    // backend: POST /api/reviews?patientId=...&doctorId=...&rating=...&comment=...
    // but prefer sending as body to a dedicated DTO endpoint; using body here
    return this.http.post<Review>(this.API_BASE, payload);
  }

  // list reviews for a doctor: GET /api/doctors/{id}/reviews
  listForDoctor(doctorId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`http://localhost:8080/api/doctors/${doctorId}/reviews`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_BASE}/${id}`);
  }
}
