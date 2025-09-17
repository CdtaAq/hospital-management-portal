import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Disease } from '../core/models/disease.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class DiseaseService {
  private readonly API = `${environment.apiUrl}/diseases`;
  constructor(private http: HttpClient) {}

  getAll(): Observable<Disease[]> { return this.http.get<Disease[]>(this.API); }
  get(id: number): Observable<Disease> { return this.http.get<Disease>(`${this.API}/${id}`); }
  create(payload: Disease): Observable<Disease> { return this.http.post<Disease>(this.API, payload); }
  update(id: number, payload: Disease): Observable<Disease> { return this.http.put<Disease>(`${this.API}/${id}`, payload); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.API}/${id}`); }
  byCategory(category: string): Observable<Disease[]> { return this.http.get<Disease[]>(`${this.API}/category/${category}`); }
}
