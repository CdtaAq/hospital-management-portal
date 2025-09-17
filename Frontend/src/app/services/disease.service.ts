import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Disease } from '../core/models/disease.model';

@Injectable({ providedIn: 'root' })
export class DiseaseService {
  private readonly API_BASE = 'http://localhost:8080/api/diseases';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Disease[]> {
    return this.http.get<Disease[]>(this.API_BASE);
  }

  get(id: number): Observable<Disease> {
    return this.http.get<Disease>(`${this.API_BASE}/${id}`);
  }

  create(payload: Disease): Observable<Disease> {
    return this.http.post<Disease>(this.API_BASE, payload);
  }

  update(id: number, payload: Disease): Observable<Disease> {
    return this.http.put<Disease>(`${this.API_BASE}/${id}`, payload);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_BASE}/${id}`);
  }
}
