import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../core/models/review.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ReviewService {
  private readonly API = `${environment.apiUrl}/reviews`;
  constructor(private http: HttpClient) {}

  create(payload: Partial<Review>): Observable<Review> {
    // backend may accept body or params; using body
    return this.http.post<Review>(this.API, payload);
  }

  listForDoctor(doctorId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${environment.apiUrl}/doctors/${doctorId}/reviews`);
  }

  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.API}/${id}`); }
}
